package com.trebol.inventory.configuration.exceptionhandler;

import com.trebol.inventory.domain.exception.QuantityInsufficientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class SaleExceptionHandler {

    @ExceptionHandler(QuantityInsufficientException.class)
    public ResponseEntity<ExceptionResponse> handleQuantityInsufficientException(QuantityInsufficientException ex){
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ex.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()
        ));
    }

}
