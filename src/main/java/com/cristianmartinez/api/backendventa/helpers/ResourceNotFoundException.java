package com.cristianmartinez.api.backendventa.helpers;

public class ResourceNotFoundException extends RuntimeException {
     public ResourceNotFoundException(String message) {
        super(message);
    }
}
