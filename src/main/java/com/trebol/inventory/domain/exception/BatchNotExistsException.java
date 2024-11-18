package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class BatchNotExistsException extends RuntimeException {
    public BatchNotExistsException() {
        super(Constants.BATCH_NOT_EXISTS);
    }
}
