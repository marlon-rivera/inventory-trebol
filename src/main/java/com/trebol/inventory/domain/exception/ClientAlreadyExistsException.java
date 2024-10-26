package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException() {
        super(Constants.CLIENT_ALREADY_EXISTS);
    }
}
