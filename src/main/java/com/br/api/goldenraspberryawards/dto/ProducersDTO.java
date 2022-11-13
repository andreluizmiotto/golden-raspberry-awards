package com.br.api.goldenraspberryawards.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProducersDTO {

    private String producer;
    private Integer interval;
    private String previousWin;
    private String followingWin;

}
