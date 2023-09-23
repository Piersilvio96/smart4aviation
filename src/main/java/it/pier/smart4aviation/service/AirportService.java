package it.pier.smart4aviation.service;

import it.pier.smart4aviation.model.dto.AirportDTO;
import it.pier.smart4aviation.model.query.AirportQuery;
import it.pier.smart4aviation.model.resource.AirportResource;
import org.springframework.http.ResponseEntity;

public interface AirportService {

    ResponseEntity<AirportResource> retrieveAirportResource(
            String airportCode,
            AirportQuery airportQuery
    );

    ResponseEntity<AirportDTO> createAirport(AirportDTO airportDTO);
}
