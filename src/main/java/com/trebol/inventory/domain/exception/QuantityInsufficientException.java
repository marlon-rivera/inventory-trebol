package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class QuantityInsufficientException extends RuntimeException {
    public QuantityInsufficientException() {
        super(Constants.QUANTITY_INSUFFICIENT);
    }
}
