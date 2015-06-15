package com.bitresolution.cep.application.engine.eventhandlers;

import lombok.extern.slf4j.Slf4j;
import org.wso2.siddhi.core.event.Event;

import java.util.Arrays;

@Slf4j
public class NoOpStreamConsumer extends StreamConsumer {
    @Override
    public void receive(Event[] events) {
        for(Event event : events) {
            log.info("Received from stream '" + event.getStreamId() + "'event:" + Arrays.toString(event.getData()));
        }
    }
}
