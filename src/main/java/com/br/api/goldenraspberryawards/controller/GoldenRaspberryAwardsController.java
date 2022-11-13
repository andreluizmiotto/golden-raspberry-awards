package com.br.api.goldenraspberryawards.controller;

import com.br.api.goldenraspberryawards.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/golden-raspberry-awards")
public class GoldenRaspberryAwardsController {

    @Autowired
    MovieService movieService;

    @GetMapping("/min-max-interval")
    public ResponseEntity<?> getMinMaxWinningInterval() {
        try {
            return ResponseEntity.ok(movieService.getMinMaxIntervalDTO());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Não foi possível obter os produtores com maior e menor intervalo de premiação");
        }
    }

}
