package com.trebol.inventory.configuration.exceptionhandler;

import com.trebol.inventory.domain.exception.CategoryAlreadyExistsException;
import com.trebol.inventory.domain.exception.CategoryNotExistsException;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CategoryExceptionHandler {

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.CONFLICT.toString(), LocalDateTime.now())
        );
    }

    @ExceptionHandler(CategoryNotExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNotExistsException(CategoryNotExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now())
        );
    }

}
