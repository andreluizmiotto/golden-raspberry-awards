package com.br.api.goldenraspberryawards.repository;

import com.br.api.goldenraspberryawards.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "SELECT producers FROM Movie WHERE winner = 'yes' GROUP BY producers HAVING COUNT(producers) > 1")
    Optional<List<String>> findAllProducersByWinnerMovies();

    List<Movie> findAllByProducersAndWinnerOrderByYearAsc(String producers, String winner);

}
