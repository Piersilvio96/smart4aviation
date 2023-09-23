package it.pier.smart4aviation.service;

import it.pier.smart4aviation.model.dto.CargoDTO;
import it.pier.smart4aviation.model.resource.FlightCreationResource;
import it.pier.smart4aviation.model.dto.FlightDTO;
import it.pier.smart4aviation.model.query.FlightQuery;
import it.pier.smart4aviation.model.resource.FlightResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FlightService {

    ResponseEntity<FlightResource> retrieveFlightResource(
            Integer flightNumber,
            FlightQuery flightQuery
    );

    ResponseEntity<FlightCreationResource> createFlight(FlightDTO flightDTO);

    ResponseEntity<List<FlightCreationResource>> importFlights(List<FlightDTO> flightDTOS);

    ResponseEntity<?> importCargos(List<CargoDTO> cargoDTOList);
}
