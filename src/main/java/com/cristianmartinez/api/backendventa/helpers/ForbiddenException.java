package com.cristianmartinez.api.backendventa.helpers;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
