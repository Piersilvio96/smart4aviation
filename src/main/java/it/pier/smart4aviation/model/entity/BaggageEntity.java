package it.pier.smart4aviation.model.entity;

import it.pier.smart4aviation.model.enums.WeightUnit;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "baggage")
public class BaggageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer weight;
    private WeightUnit weightUnit;
    private Integer pieces;
}
