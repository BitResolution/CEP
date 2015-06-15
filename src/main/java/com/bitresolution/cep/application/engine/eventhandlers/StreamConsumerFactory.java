package com.bitresolution.cep.application.engine.eventhandlers;

import org.wso2.siddhi.core.stream.output.StreamCallback;

public interface StreamConsumerFactory<T extends StreamConsumer> {

    Class<T> getStreamConsumerProduced();
    T produce();
    String getStreamConsumerName();
    String getStreamConsumerDescription();
}
