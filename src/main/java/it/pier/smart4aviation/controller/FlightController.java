package it.pier.smart4aviation.controller;

import it.pier.smart4aviation.api.FlightApi;
import it.pier.smart4aviation.model.dto.CargoDTO;
import it.pier.smart4aviation.model.resource.FlightCreationResource;
import it.pier.smart4aviation.model.dto.FlightDTO;
import it.pier.smart4aviation.model.query.FlightQuery;
import it.pier.smart4aviation.model.resource.FlightResource;
import it.pier.smart4aviation.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FlightController implements FlightApi {

    private final FlightService flightService;

    @Override
    public ResponseEntity<FlightCreationResource> createFlight(FlightDTO flightDTO) {
        return flightService.createFlight(flightDTO);
    }

    @Override
    public ResponseEntity<FlightResource> retrieveFlightResource(Integer flightNumber, FlightQuery flightQuery) {
        return flightService.retrieveFlightResource(flightNumber, flightQuery);
    }

    @Override
    public ResponseEntity<List<FlightCreationResource>> importFlights(List<FlightDTO> flightDTOS) {
        return flightService.importFlights(flightDTOS);
    }

    @Override
    public ResponseEntity<?> importCargos(List<CargoDTO> cargoDTOList) {
        return flightService.importCargos(cargoDTOList);
    }
}
