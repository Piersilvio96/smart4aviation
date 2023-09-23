package it.pier.smart4aviation.model.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightCreationResource {
    private Long id;
    private Integer flightNumber;
}
