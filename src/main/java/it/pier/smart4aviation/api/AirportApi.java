package it.pier.smart4aviation.api;

import it.pier.smart4aviation.model.dto.AirportDTO;
import it.pier.smart4aviation.model.query.AirportQuery;
import it.pier.smart4aviation.model.query.FlightQuery;
import it.pier.smart4aviation.model.resource.AirportResource;
import it.pier.smart4aviation.model.resource.FlightResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping
public interface AirportApi extends Version1{



    @GetMapping("/airports/{airportCode}")
    ResponseEntity<AirportResource> retrieveAirportResource(
            @PathVariable String airportCode,
            @Valid AirportQuery airportQuery
    );

    @PostMapping("/airports")
    ResponseEntity<AirportDTO> createAirport(@RequestBody AirportDTO airportDTO);
}
