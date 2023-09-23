package it.pier.smart4aviation.service.impl;

import it.pier.smart4aviation.model.converter.FlightResourceConverter;
import it.pier.smart4aviation.model.dto.CargoDTO;
import it.pier.smart4aviation.model.dto.FlightDTO;
import it.pier.smart4aviation.model.entity.*;
import it.pier.smart4aviation.model.query.FlightQuery;
import it.pier.smart4aviation.model.resource.FlightCreationResource;
import it.pier.smart4aviation.model.resource.FlightResource;
import it.pier.smart4aviation.repository.AirportRepository;
import it.pier.smart4aviation.repository.FlightRepository;
import it.pier.smart4aviation.service.FlightService;
import it.pier.smart4aviation.specification.FlightSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FlightResource> retrieveFlightResource(Integer flightNumber, FlightQuery flightQuery) {
        log.debug("Retrieving data about flight {} on {}", flightNumber, flightQuery.getDate());
        FlightEntity flightEntity = flightRepository.findOne(
                FlightSpecifications.withQuery(flightNumber, flightQuery))
                .orElseThrow(EntityNotFoundException::new);

        log.debug("Retrieved data about flight {} on {}", flightNumber, flightQuery.getDate());
        FlightResource resource = FlightResourceConverter.fromEntityToResource(flightEntity);
        return ResponseEntity.ok(resource);
    }

    @Override
    public ResponseEntity<FlightCreationResource> createFlight(FlightDTO flightDTO) {
        long timestamp = System.currentTimeMillis();
        AirportEntity departureAirport = airportRepository.findByIATACodeIgnoreCase(flightDTO.getDepartureAirportIATACode())
                .orElseThrow(EntityNotFoundException::new);
        AirportEntity arrivalAirport = airportRepository.findByIATACodeIgnoreCase(flightDTO.getArrivalAirportIATACode())
                .orElseThrow(EntityNotFoundException::new);


        // Creating Flight Record
        FlightEntity.FlightEntityBuilder flightEntityBuilder = FlightEntity.builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .flightNumber(flightDTO.getFlightNumber())
                .departureDate(flightDTO.getDepartureDate());

        // Creating Cargo Record
        CargoDTO cargoDTO = flightDTO.getCargoDTO();

        if (!ObjectUtils.isEmpty(cargoDTO)){
            flightEntityBuilder.cargoEntity(convertCargoDTOToEntity(cargoDTO));
        }
        FlightEntity flightEntity = flightEntityBuilder.build();


        flightEntity = flightRepository.save(flightEntity);
        FlightCreationResource resource = FlightCreationResource.builder()
                .flightNumber(flightEntity.getFlightNumber())
                .id(flightEntity.getId())
                .build();

        log.info("FlightCreation took {}ms", System.currentTimeMillis() - timestamp);
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @Override
    public ResponseEntity<List<FlightCreationResource>> importFlights(List<FlightDTO> flightDTOS) {
        long timestamp = System.currentTimeMillis();
        List<FlightCreationResource> flightCreationResourceList = new ArrayList<>();
        for (FlightDTO flightDTO : flightDTOS){
            if (!airportRepository.existsByIATACodeIgnoreCase(flightDTO.getDepartureAirportIATACode())){
                airportRepository.save(new AirportEntity(null, flightDTO.getDepartureAirportIATACode()));
            }
            if (!airportRepository.existsByIATACodeIgnoreCase(flightDTO.getArrivalAirportIATACode())){
                airportRepository.save(new AirportEntity(null, flightDTO.getArrivalAirportIATACode()));
            }
            flightCreationResourceList.add(createFlight(flightDTO).getBody());
        }
        log.info("Flight Imports took {}ms", System.currentTimeMillis() - timestamp);
        return ResponseEntity.status(HttpStatus.CREATED).body(flightCreationResourceList);
    }

    @Override
    public ResponseEntity<?> importCargos(List<CargoDTO> cargoDTOList) {
        long timestamp = System.currentTimeMillis();
        cargoDTOList.stream().forEach(cargoDTO -> {
            FlightEntity flightEntity = flightRepository.findById(cargoDTO.getFlightId() + 1)
                    .orElseThrow(EntityNotFoundException::new);
            CargoEntity cargoEntity = convertCargoDTOToEntity(cargoDTO);
            cargoEntity.setFlightEntity(flightEntity);
            flightEntity.setCargoEntity(cargoEntity);
            flightRepository.save(flightEntity);
        });

        log.info("Cargo Imports took {}ms", System.currentTimeMillis() - timestamp);
        return ResponseEntity.noContent().build();
    }


    private CargoEntity convertCargoDTOToEntity(CargoDTO cargoDTO){
        long timestamp = System.currentTimeMillis();
        CargoEntity cargoEntity = new CargoEntity();
        if (!ObjectUtils.isEmpty(cargoDTO)){
            // Creating Baggage Records
            if (!ObjectUtils.isEmpty(cargoDTO.getBaggage())){
                cargoEntity.setBaggageList(
                        cargoDTO.getBaggage().stream().map(bag ->BaggageEntity.builder()
                                .weight(bag.getWeight())
                                .pieces(bag.getPieces())
                                .weightUnit(bag.getWeightUnit())
                                .build()
                        ).collect(Collectors.toList())
                );
            }

            // Creating CargoItem Records
            if (!ObjectUtils.isEmpty(cargoDTO.getCargo())){
                cargoEntity.setCargoItems(
                        cargoDTO.getCargo().stream().map(item -> CargoItemEntity.builder()
                                .weight(item.getWeight())
                                .pieces(item.getPieces())
                                .weightUnit(item.getWeightUnit())
                                .build()).collect(Collectors.toList())
                );
            }
        }
        log.info("Cargo Entity conversion took {}ms", System.currentTimeMillis() - timestamp);
        return cargoEntity;
    }
}
