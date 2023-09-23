package it.pier.smart4aviation.service.impl;

import it.pier.smart4aviation.model.converter.AirportResourceConverter;
import it.pier.smart4aviation.model.dto.AirportDTO;
import it.pier.smart4aviation.model.entity.AirportEntity;
import it.pier.smart4aviation.model.entity.FlightEntity;
import it.pier.smart4aviation.model.query.AirportQuery;
import it.pier.smart4aviation.model.resource.AirportResource;
import it.pier.smart4aviation.repository.AirportRepository;
import it.pier.smart4aviation.repository.FlightRepository;
import it.pier.smart4aviation.service.AirportService;
import it.pier.smart4aviation.specification.FlightSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {


    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Override
    public ResponseEntity<AirportResource> retrieveAirportResource(String airportCode, AirportQuery airportQuery) {
        long timestamp = System.currentTimeMillis();
        List<FlightEntity> flightEntityList = flightRepository.findAll(FlightSpecifications.withQuery(airportCode, airportQuery));
        AirportResource resource = AirportResourceConverter.convertEntityToResource(airportCode, flightEntityList);
        log.info("Retrieve Airport resources took {}ms", System.currentTimeMillis() - timestamp);
        return ResponseEntity.ok(resource);
    }

    @Override
    public ResponseEntity<AirportDTO> createAirport(AirportDTO airportDTO) {
        long timestamp = System.currentTimeMillis();
        AirportEntity airportEntity = new AirportEntity(null, airportDTO.getIATACode());
        airportRepository.save(airportEntity);
        log.info("Airport creation took {}ms", System.currentTimeMillis() - timestamp);
        return ResponseEntity.status(HttpStatus.CREATED).body(airportDTO);
    }
}
