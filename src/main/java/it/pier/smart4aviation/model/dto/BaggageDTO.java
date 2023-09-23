package it.pier.smart4aviation.model.dto;

import it.pier.smart4aviation.model.enums.WeightUnit;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BaggageDTO {
    @NotNull
    private Integer weight;
    @NotNull
    private WeightUnit weightUnit;
    @NotNull
    private Integer pieces;
}
