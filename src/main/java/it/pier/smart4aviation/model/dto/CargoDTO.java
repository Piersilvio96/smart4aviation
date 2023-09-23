package it.pier.smart4aviation.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CargoDTO {
    private Long flightId;
    private List<CargoItemDTO> cargo;
    private List<BaggageDTO> baggage;
}
