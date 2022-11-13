package com.br.api.goldenraspberryawards.repository;

import com.br.api.goldenraspberryawards.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
