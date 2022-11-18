package com.br.api.goldenraspberryawards.service;

import com.br.api.goldenraspberryawards.domain.Movie;
import com.br.api.goldenraspberryawards.dto.MinMaxIntervalDTO;
import com.br.api.goldenraspberryawards.dto.ProducersDTO;
import com.br.api.goldenraspberryawards.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void save(List<Movie> movieList) {
        movieRepository.saveAll(movieList);
    }

    public MinMaxIntervalDTO getMinMaxIntervalDTO() {
        return filterMinMaxIntervalDTO(
                getAllProducersDTO(
                        filterTwiceWinningProducers(
                                movieRepository.findAllProducersByWinnerMovies().orElseGet(ArrayList::new))));
    }

    private MinMaxIntervalDTO filterMinMaxIntervalDTO(List<ProducersDTO> producersDTOList) {
        if (producersDTOList.isEmpty()) {
            return new MinMaxIntervalDTO();
        }

        int minInterval = Collections.min(producersDTOList, Comparator.comparingInt(ProducersDTO::getInterval)).getInterval();
        int maxInterval = Collections.max(producersDTOList, Comparator.comparingInt(ProducersDTO::getInterval)).getInterval();

        MinMaxIntervalDTO minMaxIntervalDTO = new MinMaxIntervalDTO();
        minMaxIntervalDTO.setMin(producersDTOList.stream().filter(producersDTO -> producersDTO.getInterval() == minInterval).collect(Collectors.toList()));
        minMaxIntervalDTO.setMax(producersDTOList.stream().filter(producersDTO -> producersDTO.getInterval() == maxInterval).collect(Collectors.toList()));

        return minMaxIntervalDTO;
    }

    private List<ProducersDTO> getAllProducersDTO(List<String> twiceWinningProducerList) {
        List<ProducersDTO> producersDTOList = new ArrayList<>();

        twiceWinningProducerList.forEach(producerName -> {
            List<Movie> movieList = movieRepository.findAllByProducersContainingAndWinnerOrderByYearAsc(producerName, "yes");
            producersDTOList.addAll(getProducersDTO(movieList, producerName));
        });

        return producersDTOList;
    }

    private List<String> filterTwiceWinningProducers(List<String> winnerProducers) {
        List<String> twiceWinningProducers = new ArrayList<>();

        winnerProducers.forEach(producers ->  {
            for (String producerName : producers.split(", | and ")) {
                if (twiceWinningProducers.contains(producerName)) {
                    continue;
                }
                if (movieRepository.countProducerWins(producerName) > 1) {
                    twiceWinningProducers.add(producerName);
                }
            }
        });

        return twiceWinningProducers;
    }

    private List<ProducersDTO> getProducersDTO(List<Movie> movieList, String producerName) {
        List<ProducersDTO> producersDTOList = new ArrayList<>();
        for (int i = 0; i < movieList.size() - 1; i++) {
            Movie previousWin = movieList.get(i);
            Movie followingWin = movieList.get(i + 1);

            Integer interval = Integer.parseInt(followingWin.getYear()) - Integer.parseInt(previousWin.getYear());

            producersDTOList.add(
                    new ProducersDTO(
                            producerName,
                            interval,
                            previousWin.getYear(),
                            followingWin.getYear()));
        }

        return producersDTOList;
    }
}
