package it.pier.smart4aviation.model.converter;

import it.pier.smart4aviation.model.entity.BaggageEntity;
import it.pier.smart4aviation.model.entity.CargoEntity;
import it.pier.smart4aviation.model.entity.FlightEntity;
import it.pier.smart4aviation.model.resource.AirportItemResource;
import it.pier.smart4aviation.model.resource.AirportResource;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.util.List;

public class AirportResourceConverter {

    public static AirportResource convertEntityToResource(String IATACode, List<FlightEntity> entities){
        int departures = 0;
        Integer arrivals = 0;
        BigInteger departuresPieces = BigInteger.ZERO;
        BigInteger arrivalsPieces = BigInteger.ZERO;

        for (FlightEntity flightEntity : entities){
            CargoEntity cargoEntity = flightEntity.getCargoEntity();
            if (flightEntity.getDepartureAirport().getIATACode().equalsIgnoreCase(IATACode)){
                departures++;
                departuresPieces = departuresPieces.add(
                        (!ObjectUtils.isEmpty(cargoEntity)) ? sumBaggageList(cargoEntity.getBaggageList()) : BigInteger.ZERO
                );
            }
            else if (flightEntity.getArrivalAirport().getIATACode().equalsIgnoreCase(IATACode)){
                arrivals++;
                arrivalsPieces = arrivalsPieces.add(
                        (!ObjectUtils.isEmpty(cargoEntity)) ? sumBaggageList(cargoEntity.getBaggageList()) : BigInteger.ZERO
                );
            }
            else throw new IllegalCallerException();
        }
        return AirportResource.builder()
                .arrival(AirportItemResource.builder()
                        .flightNumber(arrivals)
                        .baggagePieces(arrivalsPieces)
                        .build())
                .departure(AirportItemResource.builder()
                        .flightNumber(departures)
                        .baggagePieces(departuresPieces)
                        .build())
                .build();
    }

    private static  BigInteger sumBaggageList(List<BaggageEntity> baggageList){
        if (ObjectUtils.isEmpty(baggageList)){
            return BigInteger.ZERO;
        }
        else{
            return baggageList.stream()
                    .map(el -> BigInteger.valueOf(el.getPieces())) // Retrieve Pieces
                    .reduce(BigInteger.ZERO, BigInteger::add); // Sum pieces
        }
    }
}
