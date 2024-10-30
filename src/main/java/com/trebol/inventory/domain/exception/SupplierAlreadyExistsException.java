package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class SupplierAlreadyExistsException extends RuntimeException {
    public SupplierAlreadyExistsException() {
        super(Constants.SUPPLIER_ALREADY_EXISTS);
    }
}
