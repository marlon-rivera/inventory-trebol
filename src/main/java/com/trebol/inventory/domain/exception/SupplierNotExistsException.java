package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class SupplierNotExistsException extends RuntimeException {
    public SupplierNotExistsException() {
        super(Constants.SUPPLIER_NOT_EXISTS);
    }
}
