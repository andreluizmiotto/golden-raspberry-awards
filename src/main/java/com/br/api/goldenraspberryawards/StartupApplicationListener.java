package com.br.api.goldenraspberryawards;

import com.br.api.goldenraspberryawards.domain.Movie;
import com.br.api.goldenraspberryawards.service.MovieService;
import com.br.api.goldenraspberryawards.util.CsvReader;
import com.br.api.goldenraspberryawards.util.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StartupApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    MovieService movieService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        loadAndPersistLocalCsvFile();
    }

    private void loadAndPersistLocalCsvFile() {
        FileReader<Movie> fileReader = new CsvReader<>(Movie.class);
        Optional<List<Movie>> movieList = Optional.ofNullable(fileReader.read("movielist.csv"));
        movieList.ifPresent(movies -> movieService.save(movies));
    }
}
