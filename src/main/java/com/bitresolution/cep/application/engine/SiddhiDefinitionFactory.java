package com.bitresolution.cep.application.engine;

import com.bitresolution.cep.application.partitions.CepPartition;
import com.bitresolution.cep.application.queries.CepQuery;
import com.bitresolution.cep.application.streams.CepStream;
import org.wso2.siddhi.query.api.definition.StreamDefinition;
import org.wso2.siddhi.query.api.definition.partition.PartitionDefinition;
import org.wso2.siddhi.query.api.query.Query;
import org.wso2.siddhi.query.compiler.SiddhiCompiler;

public class SiddhiDefinitionFactory {
    public static StreamDefinition createStreamDefinition(CepStream stream) {
        StreamDefinition definition = new StreamDefinition();
        definition.name(stream.getName());
        for(CepEventAttribute attribute : stream.getEventType().getAttributes()) {
            definition.attribute(attribute.getName(), attribute.getType().siddhiType());
        }
        return definition;
    }

    public static PartitionDefinition createPartitionDefinition(CepPartition partition) {
        PartitionDefinition definition = SiddhiCompiler.parsePartitionDefinition(partition.getDefinition());
        return definition;
    }

    public static Query createQueryDefinition(CepQuery query) {
        Query definition = SiddhiCompiler.parseQuery(query.getDefinition());
        return definition;
    }
}
