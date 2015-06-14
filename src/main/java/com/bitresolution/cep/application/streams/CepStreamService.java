package com.bitresolution.cep.application.streams;

import com.bitresolution.cep.application.engine.CepEngine;
import com.bitresolution.cep.application.engine.CepEventType;
import com.bitresolution.cep.application.partitions.CepPartition;
import com.bitresolution.cep.application.rest.ResourceNotFoundException;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CepStreamService {

    private final CepStreamRepository repository;
    private final CepEngine engine;

    @Autowired
    public CepStreamService(CepStreamRepository repository, CepEngine engine) {
        this.repository = repository;
        this.engine = engine;
    }

    public List<CepStream> findAll() {
        return repository.findAll();
    }

    public CepStream findById(long id) {
        Optional<CepStream> event = repository.findById(id);
        if(event.isPresent()) {
            return event.get();
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public CepStream save(CepStream cepStream) {
        boolean isUpdate = Strings.isNullOrEmpty(cepStream.getName());
        CepStream persistedStream = repository.save(cepStream);
        if(isUpdate) {
            engine.updateStream(persistedStream);
        }
        else {
            engine.addStream(persistedStream);
        }
        return persistedStream;
    }

    public void delete(long id) {
        CepStream stream = findById(id);
        repository.delete(id);
        engine.deleteStream(stream);
    }

    public List<CepStream> findByCepEventType(CepEventType eventType) {
        return repository.findByEventType(eventType);
    }
}
