package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class ProductNotExistsException extends RuntimeException {

    public ProductNotExistsException() {
        super(Constants.PRODUCT_NOT_EXISTS);
    }

}
