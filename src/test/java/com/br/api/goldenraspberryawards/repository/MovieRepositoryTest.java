package com.br.api.goldenraspberryawards.repository;

import com.br.api.goldenraspberryawards.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private void insertData() {
        testEntityManager.merge(new Movie(
                1,
                "2001",
                "The Lord of the Rings: The Fellowship of the Ring",
                "New Line Cinema",
                "Peter Jackson",
                "yes"));
        testEntityManager.merge(new Movie(
                2,
                "2002",
                "The Lord of the Rings: The Two Towers",
                "New Line Cinema",
                "Peter Jackson",
                "yes"));
        testEntityManager.merge(new Movie(
                3,
                "2003",
                "The Lord of the Rings: The Return of the King",
                "New Line Cinema",
                "Peter Jackson",
                "yes"));
        testEntityManager.flush();
    }

    @Test
    public void shouldFindAllProducersByWinnerMovies() {
        insertData();

        List<String> movieList = movieRepository.findAllProducersByWinnerMovies().get();

        Assertions.assertNotNull(movieList);
        Assertions.assertEquals(movieList.size(), 1);
        Assertions.assertEquals(movieList.get(0), "Peter Jackson");
    }

    @Test
    public void findAllByProducersOrderByYearAsc() {
        insertData();

        List<Movie> movieList = movieRepository.findAllByProducersAndWinnerOrderByYearAsc("Peter Jackson", "yes");

        Assertions.assertNotNull(movieList);
        Assertions.assertEquals(movieList.get(0).getYear(), "2001");
        Assertions.assertEquals(movieList.get(0).getTitle(), "The Lord of the Rings: The Fellowship of the Ring");

        Assertions.assertEquals(movieList.get(1).getYear(), "2002");
        Assertions.assertEquals(movieList.get(1).getTitle(), "The Lord of the Rings: The Two Towers");

        Assertions.assertEquals(movieList.get(2).getYear(), "2003");
        Assertions.assertEquals(movieList.get(2).getTitle(), "The Lord of the Rings: The Return of the King");
    }
}