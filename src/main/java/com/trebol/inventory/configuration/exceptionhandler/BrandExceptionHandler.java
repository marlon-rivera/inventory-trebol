package com.trebol.inventory.configuration.exceptionhandler;

import com.trebol.inventory.domain.exception.BrandAlreadyExistsException;
import com.trebol.inventory.domain.exception.BrandNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class BrandExceptionHandler {

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleBrandAlreadyExistsException(BrandAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.CONFLICT.toString(), LocalDateTime.now())
        );
    }

    @ExceptionHandler(BrandNotExistsException.class)
    public ResponseEntity<ExceptionResponse> handleBrandNotExistsException(BrandNotExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now())
        );
    }

}
