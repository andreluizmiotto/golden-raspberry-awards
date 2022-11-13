package com.br.api.goldenraspberryawards.service;

import com.br.api.goldenraspberryawards.domain.Movie;
import com.br.api.goldenraspberryawards.util.CsvReader;
import com.br.api.goldenraspberryawards.util.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    MovieService movieService;

    public void loadAndPersistLocalCsvFile() {
        FileReader<Movie> fileReader = new CsvReader<>(Movie.class);
        Optional<List<Movie>> movieList = Optional.ofNullable(fileReader.read("movielist.csv"));
        movieList.ifPresent(movies -> movieService.save(movies));
    }
}
