package it.pier.smart4aviation.exception;

import lombok.Getter;

@Getter
public class SameAirportException extends IllegalArgumentException{
    public SameAirportException(String s) {
        super(s);
    }
}
