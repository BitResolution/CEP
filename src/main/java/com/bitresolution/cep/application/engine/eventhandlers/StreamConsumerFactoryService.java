package com.bitresolution.cep.application.engine.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StreamConsumerFactoryService {

    private final ApplicationContext context;
    private Map<Class<StreamConsumer>, StreamConsumerFactory<StreamConsumer>> cache;

    @Autowired
    public StreamConsumerFactoryService(ApplicationContext context) {
        this.context = context;
    }

    public List<StreamConsumerFactory> findAll() {
        ArrayList<StreamConsumerFactory> factories = new ArrayList<StreamConsumerFactory>();
        factories.addAll(context.getBeansOfType(StreamConsumerFactory.class).values());
        return factories;
    }

    public <T extends StreamConsumer> StreamConsumerFactory<T> findByStreamConsumer(Class<T> streamConsumerClass) {
        for(StreamConsumerFactory factory : findAll()) {
            if(factory.getStreamConsumerProduced().equals(streamConsumerClass))
                return factory;
        }
        throw new IllegalStateException("Unkownn StreamConsumer type: " + streamConsumerClass.getName());
    }
}
