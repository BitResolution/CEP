package com.bitresolution.cep.application.engine;

import com.bitresolution.cep.application.partitions.CepPartition;
import com.bitresolution.cep.application.queries.CepQuery;
import com.bitresolution.cep.application.streams.CepStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.query.api.definition.partition.PartitionDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.bitresolution.cep.application.engine.SiddhiDefinitionFactory.*;

@Component
public class CepEngine {

    private final SiddhiManager siddhiManager;
    private final CepInputHandlerAdapterRegistry handlerAdapterRegistry;
    private final Map<Object, String> cache;

    @Autowired
    public CepEngine(SiddhiManager siddhiManager, CepInputHandlerAdapterRegistry handlerAdapterRegistry) {
        this.siddhiManager = siddhiManager;
        this.handlerAdapterRegistry = handlerAdapterRegistry;
        cache = new ConcurrentHashMap<Object, String>();
    }

    public void addStream(CepStream stream) {
        if(siddhiManager.getStreamDefinition(stream.getName()) != null) {
            throw new StreamAlreadyExistsException(stream.getName());
        }

        InputHandler inputHandler = siddhiManager.defineStream(createStreamDefinition(stream));
        CepInputHandlerAdapter adapter = new CepInputHandlerAdapter(inputHandler, stream.getEventType());
        handlerAdapterRegistry.register(stream, adapter);
    }

    public void deleteStream(CepStream stream) {
        if(siddhiManager.getStreamDefinition(stream.getName()) != null) {
            throw new StreamDoesNotExistsException(stream.getName());
        }

        siddhiManager.removeStream(stream.getName());
        handlerAdapterRegistry.unregister(stream);
    }

    public void updateStream(CepStream stream) {
        deleteStream(stream);
        addStream(stream);
    }

    public void addPartition(CepPartition partition) {
        if(cache.get(partition) != null) {
            throw new PartitionAlreadyExistsException(partition);
        }

        PartitionDefinition definition = createPartitionDefinition(partition);
        siddhiManager.definePartition(definition);
        cache.put(partition, definition.getPartitionId());
    }

    public void deletePartition(CepPartition partition) {
        if(cache.get(partition) == null) {
            throw new PartitionDoesNotExistException(partition);
        }
        siddhiManager.removePartition(partition.getName());
        cache.remove(partition);
    }

    public void updatePartition(CepPartition partition) {
        deletePartition(partition);
        addPartition(partition);
    }

    public void addQuery(CepQuery query) {
        if(cache.get(query) != null) {
            throw new QueryAlreadyExistsException(query);
        }
        String siddhiId = siddhiManager.addQuery(createQueryDefinition(query));
        cache.put(query, siddhiId);
    }

    public void deleteQuery(CepQuery query) {
        String queryId = cache.get(query);
        if(queryId == null) {
            throw new QueryDoesNotExistException(query);
        }
        siddhiManager.removeQuery(queryId);
        cache.remove(query);
    }

    public void updateQuery(CepQuery query) {
        deleteQuery(query);
        addQuery(query);
    }
}
