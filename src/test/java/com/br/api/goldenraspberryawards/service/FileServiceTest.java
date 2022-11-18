package com.br.api.goldenraspberryawards.service;

import com.br.api.goldenraspberryawards.domain.Movie;
import com.br.api.goldenraspberryawards.util.CsvReader;
import com.br.api.goldenraspberryawards.util.FileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.io.StringReader;
import java.util.List;
import java.util.Optional;

import static com.br.api.goldenraspberryawards.utils.FileData.getFileData;

@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class FileServiceTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void loadAndPersistCsvFile() {
        FileReader<Movie> fileReader = new CsvReader<>(Movie.class);
        Optional<List<Movie>> movieList = Optional.ofNullable(
                fileReader.read(
                        new StringReader(getFileData())));

        Assertions.assertTrue(movieList.isPresent());
        Assertions.assertEquals(Integer.parseInt(movieList.get().get(0).getYear()), 1980);
        Assertions.assertEquals(movieList.get().get(0).getTitle(), "Can't Stop the Music");
        Assertions.assertEquals(movieList.get().get(0).getProducers(), "Allan Carr");
        Assertions.assertEquals(movieList.get().get(1).getTitle(), "Cruising");
        Assertions.assertEquals(movieList.get().get(1).getStudios(), "Lorimar Productions, United Artists");
        Assertions.assertEquals(movieList.get().get(movieList.get().size() - 1).getTitle(), "Rambo: Last Blood");

        for (Movie movie : movieList.get()) {
            String movieTitle = movie.getTitle();
            Integer id = testEntityManager.persistAndGetId(movie, Integer.class);

            Assertions.assertEquals(testEntityManager.find(Movie.class, id).getTitle(), movieTitle);
        }
    }

}