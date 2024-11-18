package com.trebol.inventory.domain.exception;

import com.trebol.inventory.utils.Constants;

public class ClientNotExistsException extends RuntimeException {
    public ClientNotExistsException() {
        super(Constants.CLIENT_NOT_EXISTS);
    }
}
