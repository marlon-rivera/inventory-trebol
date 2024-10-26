package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class CategoryNotExistsException extends RuntimeException {
    public CategoryNotExistsException() {
        super(Constants.CATEGORY_NOT_EXISTS);
    }
}
