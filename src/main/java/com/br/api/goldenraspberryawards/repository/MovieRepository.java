package com.br.api.goldenraspberryawards.repository;

import com.br.api.goldenraspberryawards.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "SELECT producers FROM Movie WHERE winner = 'yes' GROUP BY producers")
    Optional<List<String>> findAllProducersByWinnerMovies();

    @Query(value = "SELECT count(id) FROM Movie WHERE winner = 'yes' AND producers LIKE CONCAT('%', ?1, '%')")
    Integer countProducerWins(String producer);

    List<Movie> findAllByProducersContainingAndWinnerOrderByYearAsc(String producer, String winner);

}
