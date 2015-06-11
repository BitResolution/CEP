package com.bitresolution.cep.application.rest;

public class ResourceAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -6445943330573843327L;

    public ResourceAlreadyExistsException(final String message) {
        super(message);
    }

    public ResourceAlreadyExistsException() {
        super();
    }
}
