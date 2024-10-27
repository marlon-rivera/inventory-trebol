package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class UnitMeasureAlreadyExistsException extends RuntimeException{

    public UnitMeasureAlreadyExistsException() {
        super(Constants.UNIT_MEASURE_ALREADY_EXISTS);
    }

}
