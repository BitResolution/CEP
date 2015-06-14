package com.bitresolution.cep.web.application;

import com.bitresolution.cep.application.engine.eventtypes.CepEventTypeAttributeType;
import com.bitresolution.cep.application.engine.eventtypes.CepEventType;
import com.bitresolution.cep.application.engine.eventtypes.CepEventTypeService;
import com.bitresolution.cep.application.partitions.CepPartition;
import com.bitresolution.cep.application.partitions.CepPartitionService;
import com.bitresolution.cep.application.streams.CepStream;
import com.bitresolution.cep.application.streams.CepStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.bitresolution.cep.application.engine.eventtypes.CepEventTypeAttribute.cepEventAttribute;
import static com.bitresolution.cep.application.engine.eventtypes.CepEventType.cepEventType;
import static java.util.Arrays.asList;

@Component
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final String OUT_OF_MEMORY_EVENT = "OutOfMemoryEvent";
    private static final String GC_EVENT = "GarbageCollectionEvent";
    private static final String GARBAGE_COLLECTIONS = "GarbageCollections";
    private static final String OUT_OF_MEMORY_ERRORS = "OutOfMemoryErrors";


    private final CepEventTypeService eventTypeService;
    private final CepStreamService streamService;
    private final CepPartitionService partitionService;

    private final Map<String, CepEventType> eventTypes;
    private final Map<String, CepStream> streams;
    private final Map<String, CepPartition> partitions;

    @Autowired
    public DataBootstrap(CepPartitionService partitionService, CepEventTypeService eventTypeService, CepStreamService streamService) {
        this.partitionService = partitionService;
        this.eventTypeService = eventTypeService;
        this.streamService = streamService;

        this.eventTypes = new HashMap<String, CepEventType>();
        this.streams = new HashMap<String, CepStream>();
        this.partitions = new HashMap<String, CepPartition>();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        bootstrapEventTypes();
        bootstrapStreams();
    }

    private void bootstrapEventTypes() {
        create(cepEventType()
                        .name(GC_EVENT)
                        .attributes(asList(
                                cepEventAttribute().name("machine_id").type(CepEventTypeAttributeType.STRING).build(),
                                cepEventAttribute().name("full").type(CepEventTypeAttributeType.BOOLEAN).build(),
                                cepEventAttribute().name("live_heap_size_before").type(CepEventTypeAttributeType.LONG).build(),
                                cepEventAttribute().name("live_heap_size_after").type(CepEventTypeAttributeType.LONG).build(),
                                cepEventAttribute().name("time_taken").type(CepEventTypeAttributeType.LONG).build()
                        ))
                        .build()
        );

        create(cepEventType()
                        .name(OUT_OF_MEMORY_EVENT)
                        .attributes(asList(
                                cepEventAttribute().name("machine_id").type(CepEventTypeAttributeType.STRING).build()
                        ))
                        .build()
        );
    }

    private void bootstrapStreams() {
        create(CepStream.cepStream().name(GARBAGE_COLLECTIONS).eventType(eventTypes.get(GC_EVENT)).build());
        create(CepStream.cepStream().name(OUT_OF_MEMORY_ERRORS).eventType(eventTypes.get(OUT_OF_MEMORY_EVENT)).build());
    }

    private void create(CepEventType eventType) {
        CepEventType result = eventTypeService.save(eventType);
        eventTypes.put(result.getName(), result);
    }

    private void create(CepStream stream) {
        CepStream result = streamService.save(stream);
        streams.put(result.getName(), result);
    }

    private void create(CepPartition partition) {
        CepPartition result = partitionService.save(partition);
        partitions.put(result.getName(), result);
    }
}
