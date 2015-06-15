package com.bitresolution.cep.application.engine;

import com.bitresolution.cep.application.engine.eventhandlers.CepEventHandler;
import com.bitresolution.cep.application.engine.eventhandlers.StreamConsumer;

public class EventHandlerAlreadyExistsException extends RuntimeException {

    public EventHandlerAlreadyExistsException(CepEventHandler consumer) {
        super("CepEventHandler '" + consumer.getName() + "' does not exist");
    }
}