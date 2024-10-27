package com.trebol.inventory.configuration.exceptionhandler;

import com.trebol.inventory.domain.exception.UnitMeasureAlreadyExistsException;
import com.trebol.inventory.domain.exception.UnitMeasureNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UnitMeasureExceptionHandler {

    @ExceptionHandler(UnitMeasureAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUnitMeasureAlreadyExistsException(UnitMeasureAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.CONFLICT.toString(), LocalDateTime.now())
        );
    }

    @ExceptionHandler(UnitMeasureNotExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUnitMeasureNotExistsException(UnitMeasureNotExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now())
        );
    }

}
