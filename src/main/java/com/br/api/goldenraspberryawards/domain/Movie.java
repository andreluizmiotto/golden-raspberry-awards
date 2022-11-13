package com.br.api.goldenraspberryawards.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String year;
    private String title;
    private String studios;
    private String producers;

    @Nullable
    private String winner;
}
