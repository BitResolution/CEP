package com.bitresolution.cep.application.engine.eventhandlers;

import org.springframework.stereotype.Component;

@Component
public class LoggingStreamConsumerFactory implements StreamConsumerFactory<LoggingStreamConsumer> {
    @Override
    public Class<LoggingStreamConsumer> getStreamConsumerProduced() {
        return LoggingStreamConsumer.class;
    }

    @Override
    public LoggingStreamConsumer produce() {
        return new LoggingStreamConsumer();
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
