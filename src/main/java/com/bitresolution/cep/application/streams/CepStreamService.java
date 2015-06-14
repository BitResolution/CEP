package com.bitresolution.cep.application.streams;

import com.bitresolution.cep.application.engine.CepEventType;
import com.bitresolution.cep.application.rest.ResourceNotFoundException;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CepStreamService {

    private final CepStreamRepository repository;

    @Autowired
    public CepStreamService(CepStreamRepository repository) {
        this.repository = repository;
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
        return repository.save(cepStream);
    }

    public void delete(long id) {
        repository.delete(id);
    }

    public List<CepStream> findByCepEventType(CepEventType eventType) {
        return repository.findByEventType(eventType);
    }
}
