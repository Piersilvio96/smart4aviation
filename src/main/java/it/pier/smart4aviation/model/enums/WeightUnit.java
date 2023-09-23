package it.pier.smart4aviation.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum WeightUnit {
    kg(BigDecimal.ONE), lb(BigDecimal.valueOf(0.45359237));

    private final BigDecimal unit;

    WeightUnit(BigDecimal unit) {
        this.unit = unit;
    }

    @JsonCreator
    public WeightUnit fromString(String value){
        return valueOf(value.toLowerCase());
    }
}
