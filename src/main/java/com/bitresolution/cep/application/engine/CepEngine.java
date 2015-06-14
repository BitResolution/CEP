package com.bitresolution.cep.application.engine;

import com.bitresolution.cep.application.partitions.CepPartition;
import com.bitresolution.cep.application.queries.CepQuery;
import com.bitresolution.cep.application.streams.CepStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.query.api.definition.partition.PartitionDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.bitresolution.cep.application.engine.SiddhiDefinitionFactory.*;

@Slf4j
@Component
//TODO fix circular dependency with CepStreamService via CepInputHandlerAdapterRegistry so can use constructor injection
public class CepEngine {

    @Autowired
    private SiddhiManager siddhiManager;
    @Autowired
    private CepInputHandlerAdapterRegistry handlerAdapterRegistry;
    private Map<Object, String> cache;

    public CepEngine() {
        cache = new ConcurrentHashMap<Object, String>();
    }

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
        log.info("Added Siddhi stream: " + stream);
    }

    public void deleteStream(CepStream stream) {
        if(siddhiManager.getStreamDefinition(stream.getName()) != null) {
            throw new StreamDoesNotExistsException(stream.getName());
        }

        siddhiManager.removeStream(stream.getName());
        handlerAdapterRegistry.unregister(stream);
        log.info("Removed Siddhi stream: " + stream);
    }

    public void updateStream(CepStream stream) {
        deleteStream(stream);
        addStream(stream);
        log.info("Updated Siddhi stream: " + stream);
    }

    public void addPartition(CepPartition partition) {
        if(cache.get(partition) != null) {
            throw new PartitionAlreadyExistsException(partition);
        }

        PartitionDefinition definition = createPartitionDefinition(partition);
        siddhiManager.definePartition(definition);
        cache.put(partition, definition.getPartitionId());
        log.info("Added Siddhi partition: " + partition);
    }

    public void deletePartition(CepPartition partition) {
        if(cache.get(partition) == null) {
            throw new PartitionDoesNotExistException(partition);
        }
        siddhiManager.removePartition(partition.getName());
        cache.remove(partition);
        log.info("Removed Siddhi partition: " + partition);
    }

    public void updatePartition(CepPartition partition) {
        deletePartition(partition);
        addPartition(partition);
        log.info("Updated Siddhi partition: " + partition);
    }

    public void addQuery(CepQuery query) {
        if(cache.get(query) != null) {
            throw new QueryAlreadyExistsException(query);
        }
        String siddhiId = siddhiManager.addQuery(createQueryDefinition(query));
        cache.put(query, siddhiId);
        log.info("Added Siddhi query: " + query);
    }

    public void deleteQuery(CepQuery query) {
        String queryId = cache.get(query);
        if(queryId == null) {
            throw new QueryDoesNotExistException(query);
        }
        siddhiManager.removeQuery(queryId);
        cache.remove(query);
        log.info("Removed Siddhi query: " + query);
    }

    public void updateQuery(CepQuery query) {
        deleteQuery(query);
        addQuery(query);
        log.info("Updated Siddhi query: " + query);
    }
}
