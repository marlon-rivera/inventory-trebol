package com.trebol.inventory.domain.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("El empleado no existe.");
    }
}
