package com.trebol.inventory.configuration.exceptionhandler;

import com.trebol.inventory.domain.exception.SupplierAlreadyExistsException;
import com.trebol.inventory.domain.exception.SupplierNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class SupplierExceptionHandler {

    @ExceptionHandler(SupplierNotExistsException.class)
    public ResponseEntity<ExceptionResponse> handleSupplierNotExistsException(SupplierNotExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now())
        );
    }

    @ExceptionHandler(SupplierAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleSupplierAlreadyExistsException(SupplierAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.CONFLICT.toString(), LocalDateTime.now())
        );
    }
}
