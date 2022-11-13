package com.br.api.goldenraspberryawards.service;

import com.br.api.goldenraspberryawards.domain.Movie;
import com.br.api.goldenraspberryawards.dto.MinMaxIntervalDTO;
import com.br.api.goldenraspberryawards.dto.ProducersDTO;
import com.br.api.goldenraspberryawards.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void save(List<Movie> movieList) {
        movieRepository.saveAll(movieList);
    }

    public MinMaxIntervalDTO getMinMaxIntervalDTO() {
        MinMaxIntervalDTO minMaxIntervalDTO = new MinMaxIntervalDTO();

        Optional<List<String>> producersList = movieRepository.findAllProducersByWinnerMovies();
        if (producersList.isEmpty() || producersList.get().isEmpty()) {
            return minMaxIntervalDTO;
        }

        for (String producers : producersList.get()) {
            List<Movie> movieList = movieRepository.findAllByProducersOrderByYearAsc(producers);

            minMaxIntervalDTO.getMin().add(getProducersInterval(movieList, true));
            minMaxIntervalDTO.getMax().add(getProducersInterval(movieList, false));
        }

        int minInterval = Collections.min(minMaxIntervalDTO.getMin(), Comparator.comparingInt(ProducersDTO::getInterval)).getInterval();
        int maxInterval = Collections.max(minMaxIntervalDTO.getMax(), Comparator.comparingInt(ProducersDTO::getInterval)).getInterval();

        minMaxIntervalDTO.getMin().removeIf(producersDTO -> producersDTO.getInterval() > minInterval);
        minMaxIntervalDTO.getMax().removeIf(producersDTO -> producersDTO.getInterval() < maxInterval);

        return minMaxIntervalDTO;
    }

    private ProducersDTO getProducersInterval(List<Movie> movieList, Boolean isMinInterval) {
        ProducersDTO producersDTO = new ProducersDTO();
        producersDTO.setProducer(movieList.get(0).getProducers());

        for (int i = 0; i < movieList.size() - 1; i++) {
            Movie previousWin = movieList.get(i);
            Movie followingWin = movieList.get(i + 1);

            Integer interval = Integer.parseInt(followingWin.getYear()) - Integer.parseInt(previousWin.getYear());
            if (producersDTO.getInterval() == null ||
                    ((isMinInterval && (interval < producersDTO.getInterval())) || (!isMinInterval && (interval > producersDTO.getInterval())))) {
                producersDTO.setInterval(interval);
                producersDTO.setPreviousWin(previousWin.getYear());
                producersDTO.setFollowingWin(followingWin.getYear());
            }
        }
        return producersDTO;
    }
}
