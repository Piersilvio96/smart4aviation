package it.pier.smart4aviation.api;


import it.pier.smart4aviation.model.dto.CargoDTO;
import it.pier.smart4aviation.model.dto.FlightDTO;
import it.pier.smart4aviation.model.query.FlightQuery;
import it.pier.smart4aviation.model.resource.FlightCreationResource;
import it.pier.smart4aviation.model.resource.FlightResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface FlightApi extends Version1{

    @PostMapping("/flights")
    ResponseEntity<FlightCreationResource> createFlight(@RequestBody FlightDTO flightDTO);

    @GetMapping("/flights/{flightNumber}")
    ResponseEntity<FlightResource> retrieveFlightResource(
            @PathVariable Integer flightNumber,
            @Valid FlightQuery flightQuery
    );

    @PostMapping("/flights/import")
    ResponseEntity<List<FlightCreationResource>> importFlights(@RequestBody List<FlightDTO> flightDTOS);

    @PostMapping("/flights/cargo/imports")
    ResponseEntity<?> importCargos(@RequestBody List<CargoDTO> cargoDTOList);

}
