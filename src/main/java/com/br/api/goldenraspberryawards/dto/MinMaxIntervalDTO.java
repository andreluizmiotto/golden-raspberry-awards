package com.br.api.goldenraspberryawards.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MinMaxIntervalDTO {

    private List<ProducersDTO> min;
    private List<ProducersDTO> max;

}
