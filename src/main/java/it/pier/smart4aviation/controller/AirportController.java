package it.pier.smart4aviation.controller;

import it.pier.smart4aviation.api.AirportApi;
import it.pier.smart4aviation.model.dto.AirportDTO;
import it.pier.smart4aviation.model.query.AirportQuery;
import it.pier.smart4aviation.model.resource.AirportResource;
import it.pier.smart4aviation.service.AirportService;
import it.pier.smart4aviation.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AirportController implements AirportApi {

    private final FlightService flightService;
    private final AirportService airportService;



    @Override
    public ResponseEntity<AirportResource> retrieveAirportResource(String airportCode, AirportQuery airportQuery) {
        return airportService.retrieveAirportResource(airportCode, airportQuery);
    }

    @Override
    public ResponseEntity<AirportDTO> createAirport(AirportDTO airportDTO) {
        return airportService.createAirport(airportDTO);
    }
}
