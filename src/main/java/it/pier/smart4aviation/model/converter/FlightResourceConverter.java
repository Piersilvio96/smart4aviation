package it.pier.smart4aviation.model.converter;

import it.pier.smart4aviation.model.entity.FlightEntity;
import it.pier.smart4aviation.model.enums.WeightUnit;
import it.pier.smart4aviation.model.resource.FlightResource;

import java.math.BigDecimal;

public class FlightResourceConverter {

    public static FlightResource fromEntityToResource(FlightEntity flightEntity) {
        FlightResource flightResource = FlightResource.builder()
                .baggageWeight(flightEntity
                        .getCargoEntity().getBaggageList().stream().map(el -> new BigDecimal(el.getWeight()).multiply(el.getWeightUnit().getUnit())
                        ).reduce(BigDecimal.ZERO, BigDecimal::add))
                .cargoWeight(flightEntity.getCargoEntity().getCargoItems().stream()
                        .map(el -> new BigDecimal(el.getWeight()).multiply(el.getWeightUnit().getUnit())
                        ).reduce(BigDecimal.ZERO, BigDecimal::add))
                .unit(WeightUnit.kg)
                .build();
        flightResource.setTotalWeight(flightResource.getBaggageWeight().add(flightResource.getCargoWeight()));
        return flightResource;
    }
}
