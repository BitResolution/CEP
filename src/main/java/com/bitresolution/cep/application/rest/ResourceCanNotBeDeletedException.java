package com.bitresolution.cep.application.rest;

public class ResourceCanNotBeDeletedException extends ResourceException {
    public ResourceCanNotBeDeletedException() {
        super();
    }

    public ResourceCanNotBeDeletedException(String s) {
        super(s);
    }

    public ResourceCanNotBeDeletedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ResourceCanNotBeDeletedException(Throwable throwable) {
        super(throwable);
    }
}
