package com.trebol.inventory.configuration.exceptionhandler;

import com.trebol.inventory.domain.exception.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ExceptionResponse> handleProductNotExistsException(ProductNotExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now())
        );
    }

}
