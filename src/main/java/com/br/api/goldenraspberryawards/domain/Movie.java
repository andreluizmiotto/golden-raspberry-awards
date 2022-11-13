package com.br.api.goldenraspberryawards.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // year é palavra reservada no H2 :(
    @Column(name = "RELEASEYEAR")
    private String year;

    private String title;
    private String studios;
    private String producers;

    @Nullable
    private String winner;
}
