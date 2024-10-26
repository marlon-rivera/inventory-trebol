package com.trebol.inventory.configuration.exceptionhandler;

import com.trebol.inventory.domain.exception.ClientAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleClientAlreadyExistsException(ClientAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.CONFLICT.toString(), LocalDateTime.now())
        );
    }

}
