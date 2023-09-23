package it.pier.smart4aviation.model.validator;

import it.pier.smart4aviation.exception.SameAirportException;
import it.pier.smart4aviation.model.dto.FlightDTO;

public class FlightValidator {

    public static void validateFlight(FlightDTO flightDTO){
        if (flightDTO.getArrivalAirportIATACode().equals(flightDTO.getDepartureAirportIATACode())){
            throw new SameAirportException("Departure and Arrival Airport cannot be the same");
        }
    }
}
