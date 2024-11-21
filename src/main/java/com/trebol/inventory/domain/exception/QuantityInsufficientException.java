package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class QuantityInsufficientException extends RuntimeException {
    public QuantityInsufficientException(String product) {
        super(Constants.QUANTITY_INSUFFICIENT + " " + product);
    }
}
