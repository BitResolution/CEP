package com.bitresolution.cep.application.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//TODO make the publish happen in threads
public class CepEventPublisher {

    private final CepEventTypeService eventTypeService;
    private final CepInputHandlerAdapterRegistry inputHandlerAdapterRegistry;

    @Autowired
    public CepEventPublisher(CepEventTypeService eventTypeService, CepInputHandlerAdapterRegistry inputHandlerAdapterRegistry) {
        this.eventTypeService = eventTypeService;
        this.inputHandlerAdapterRegistry = inputHandlerAdapterRegistry;
    }

    public void publish(CepEvent event) {
        CepEventType type = eventTypeService.findByName(event.getType());
        for(CepInputHandlerAdapter adapter : inputHandlerAdapterRegistry.getForEventType(type)) {
            adapter.send(event);
        }
    }
}
