package com.trebol.inventory.domain.exception;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException() {
        super("No hay transacciones en el rango de fechas especificado.");
    }

}
