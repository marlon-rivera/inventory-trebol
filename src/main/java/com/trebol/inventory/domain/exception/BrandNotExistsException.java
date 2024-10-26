package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class BrandNotExistsException extends RuntimeException {
    public BrandNotExistsException() {
        super(Constants.BRAND_NOT_EXISTS);
    }
}
