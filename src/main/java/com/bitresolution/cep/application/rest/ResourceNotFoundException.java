package com.bitresolution.cep.application.rest;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -848301337435697583L;

    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super();
    }
}
