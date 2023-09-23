package it.pier.smart4aviation.model.resource;

import it.pier.smart4aviation.model.enums.WeightUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightResource {
    private BigDecimal cargoWeight;
    private BigDecimal baggageWeight;
    private BigDecimal totalWeight;
    private WeightUnit unit;
}
