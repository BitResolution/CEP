package com.bitresolution.cep.application.engine;

import com.bitresolution.cep.application.engine.eventtypes.CepEventType;
import com.bitresolution.cep.application.streams.CepStream;
import com.bitresolution.cep.application.streams.CepStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CepInputHandlerAdapterRegistry {

    private final CepStreamService streamService;
    private final Map<String, CepInputHandlerAdapter> registry;

    @Autowired
    public CepInputHandlerAdapterRegistry(CepStreamService streamService) {
        this.streamService = streamService;
        registry = new HashMap<String, CepInputHandlerAdapter>();
    }

    public Iterable<? extends CepInputHandlerAdapter> getForEventType(CepEventType type) {
        List<CepStream> streams = streamService.findByCepEventType(type);
        List<CepInputHandlerAdapter> adapters = new ArrayList<CepInputHandlerAdapter>();
        for(CepStream stream : streams) {
            CepInputHandlerAdapter adapter = registry.get(stream.getName());
            adapters.add(adapter);
        }
        return adapters;
    }

    public void register(CepStream stream, CepInputHandlerAdapter adapter) {
        registry.put(stream.getName(), adapter);
    }

    public void unregister(CepStream stream) {
        registry.remove(stream.getName());
    }
}
