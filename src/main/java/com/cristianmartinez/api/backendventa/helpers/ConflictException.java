package com.cristianmartinez.api.backendventa.helpers;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}

