package com.br.api.goldenraspberryawards.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MinMaxIntervalDTO {

    public MinMaxIntervalDTO() {
        min = new ArrayList<>();
        max = new ArrayList<>();
    }

    private List<ProducerDTO> min;
    private List<ProducerDTO> max;

}
