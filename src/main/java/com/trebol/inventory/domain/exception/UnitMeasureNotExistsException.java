package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class UnitMeasureNotExistsException extends RuntimeException {
    public UnitMeasureNotExistsException() {
        super(Constants.UNIT_MEASURE_NOT_EXISTS);
    }
}
