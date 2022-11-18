package com.br.api.goldenraspberryawards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProducersDTO {

    private String producer;
    private Integer interval;
    private String previousWin;
    private String followingWin;

}
