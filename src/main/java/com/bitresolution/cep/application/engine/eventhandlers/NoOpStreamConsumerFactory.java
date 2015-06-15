package com.bitresolution.cep.application.engine.eventhandlers;

import org.springframework.stereotype.Component;

@Component
public class NoOpStreamConsumerFactory implements StreamConsumerFactory<NoOpStreamConsumer> {
    @Override
    public Class<NoOpStreamConsumer> getStreamConsumerProduced() {
        return NoOpStreamConsumer.class;
    }

    @Override
    public NoOpStreamConsumer produce() {
        return new NoOpStreamConsumer();
    }

    @Override
    public String getStreamConsumerName() {
        return "NoOp";
    }

    @Override
    public String getStreamConsumerDescription() {
        return "Logs the events";
    }
}
