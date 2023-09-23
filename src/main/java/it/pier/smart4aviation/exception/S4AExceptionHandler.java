package it.pier.smart4aviation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
@Slf4j
public class S4AExceptionHandler{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericError(Exception e){
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<?> handleEntityNotFound(EntityNotFoundException e){
        log.error(e.getLocalizedMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SameAirportException.class)
    ResponseEntity<?> handleSameAirportException(SameAirportException e){
        log.error(e.getLocalizedMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
