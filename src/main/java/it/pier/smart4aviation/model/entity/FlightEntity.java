package it.pier.smart4aviation.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "flight")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer flightNumber;
    private LocalDateTime departureDate;
    @ManyToOne
    private AirportEntity arrivalAirport;
    @ManyToOne
    private AirportEntity departureAirport;
    @OneToOne(mappedBy = "flightEntity", cascade = CascadeType.ALL)
    private CargoEntity cargoEntity;
}
