package com.bitresolution.cep.web.application;

import com.bitresolution.cep.application.events.AttributeType;
import com.bitresolution.cep.application.events.CepEventService;
import com.bitresolution.cep.application.events.CepEventType;
import com.bitresolution.cep.application.events.CepEventTypeService;
import com.bitresolution.cep.application.streams.CepStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import static com.bitresolution.cep.application.events.CepEventAttribute.cepEventAttribute;
import static com.bitresolution.cep.application.events.CepEventType.cepEventType;
import static java.util.Arrays.asList;

@Component
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CepEventService eventService;
    private final CepEventTypeService eventTypeService;
    private final CepStreamService streamService;

    @Autowired
    public DataBootstrap(CepEventService eventService, CepEventTypeService eventTypeService, CepStreamService streamService) {
        this.eventService = eventService;
        this.eventTypeService = eventTypeService;
        this.streamService = streamService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        bootstrapEventTypes();
        bootstrapStreams();
    }

    private void bootstrapEventTypes() {

        CepEventType speedingEvent = cepEventType().name("SpeedingEvent").attributes(asList(
                cepEventAttribute().name("speed").type(AttributeType.FLOAT).build(),
                cepEventAttribute().name("vechicle_id").type(AttributeType.STRING).build()
        )).build();

        eventTypeService.save(speedingEvent);
    }

    private void bootstrapStreams() {
    }
}
