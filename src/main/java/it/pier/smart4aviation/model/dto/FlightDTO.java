package it.pier.smart4aviation.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import it.pier.smart4aviation.utils.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    @NotNull
    private Integer flightNumber;
    @NotNull
    private String departureAirportIATACode;
    @NotNull
    private String arrivalAirportIATACode;
    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime departureDate;
    private CargoDTO cargoDTO;

}
