package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class BrandAlreadyExistsException extends RuntimeException {
    public BrandAlreadyExistsException() {
        super(Constants.BRAND_ALREADY_EXISTS);
    }
}
