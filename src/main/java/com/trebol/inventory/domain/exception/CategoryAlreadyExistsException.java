package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super(Constants.CATEGORY_ALREADY_EXISTS);
    }
}
