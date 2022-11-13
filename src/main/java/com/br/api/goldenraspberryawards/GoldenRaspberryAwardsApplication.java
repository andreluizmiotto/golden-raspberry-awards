package com.br.api.goldenraspberryawards;

import com.br.api.goldenraspberryawards.domain.Movie;
import com.br.api.goldenraspberryawards.util.CsvReader;
import com.br.api.goldenraspberryawards.util.FileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GoldenRaspberryAwardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoldenRaspberryAwardsApplication.class, args);
		loadCsvFile();
	}

	private static void loadCsvFile() {
		FileReader<Movie> fileReader = new CsvReader<>(Movie.class);
		List<Movie> csvMovieDTOList = fileReader.read("movielist.csv");

		for (Movie movie : csvMovieDTOList) {
			System.out.println(movie.getTitle());
		}
	}

}
