package it.pier.smart4aviation.model.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportResource {
    private AirportItemResource departure;
    private AirportItemResource arrival;
}
