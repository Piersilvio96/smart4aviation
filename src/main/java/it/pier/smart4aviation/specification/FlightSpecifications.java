package it.pier.smart4aviation.specification;

import it.pier.smart4aviation.model.entity.AirportEntity;
import it.pier.smart4aviation.model.entity.FlightEntity;
import it.pier.smart4aviation.model.query.AirportQuery;
import it.pier.smart4aviation.model.query.FlightQuery;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlightSpecifications {

    private static final String FLIGHT_NUMBER_COLUMN = "flightNumber";
    private static final String FLIGHT_DATE_COLUMN = "departureDate";
    private static final String AIRPORT_DEPARTURE_COLUMN = "departureAirport";
    private static final String AIRPORT_ARRIVAL_COLUMN = "arrivalAirport";
    private static final String AIRPORT_IATA_CODE_COLUMN = "IATACode";

    public static Specification<FlightEntity> withQuery(Integer flightNumber, FlightQuery flightQuery){
        return withFlightNumber(flightNumber).and(withDate(flightQuery.getDate()));
    }

    public static Specification<FlightEntity> withQuery(String IATACode, AirportQuery airportQuery){
        return withAirport(IATACode).and(withDate(airportQuery.getDate()));
    }

    private static Specification<FlightEntity> withFlightNumber(Integer flightNumber){
        if (flightNumber == null){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(FLIGHT_NUMBER_COLUMN), flightNumber);
    }

    private static Specification<FlightEntity> withDate(LocalDate localDate){
        if (localDate == null){
            return null;
        }
        LocalDateTime fromDate = localDate.atStartOfDay();
        LocalDateTime toDate = localDate.plusDays(1).atStartOfDay();
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(
                root.get(FLIGHT_DATE_COLUMN), fromDate, toDate);
    }

    private static Specification<FlightEntity> withAirport(String IATACode){
        if (IATACode == null){
            return null;
        }
        return ((root, query, criteriaBuilder) ->{
            Join<FlightEntity, AirportEntity> departureAirport = root.join(AIRPORT_DEPARTURE_COLUMN);
            Join<FlightEntity, AirportEntity> arrivalAirport = root.join(AIRPORT_DEPARTURE_COLUMN);

                return criteriaBuilder.or(criteriaBuilder.equal(arrivalAirport.get(AIRPORT_IATA_CODE_COLUMN), IATACode),
                        criteriaBuilder.equal(departureAirport.get(AIRPORT_IATA_CODE_COLUMN), IATACode));

        });
    }
}
