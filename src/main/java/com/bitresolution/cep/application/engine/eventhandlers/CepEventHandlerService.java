package com.bitresolution.cep.application.engine.eventhandlers;

import com.bitresolution.cep.application.engine.CepEngine;
import com.bitresolution.cep.application.rest.ResourceNotFoundException;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CepEventHandlerService {

    private final CepEventHandlerRepository eventHandlerRepository;
    private final CepEngine engine;

    @Autowired
    public CepEventHandlerService(CepEventHandlerRepository eventHandlerRepository, CepEngine engine) {
        this.eventHandlerRepository = eventHandlerRepository;
        this.engine = engine;
    }

    public List<CepEventHandler> findAll() {
        return eventHandlerRepository.findAll();
    }

    public CepEventHandler save(CepEventHandler event) {
        CepEventHandler handler = eventHandlerRepository.save(event);
        engine.addEventHandler(handler);
        return handler;
    }

    public CepEventHandler findById(long id) {
        Optional<CepEventHandler> event = eventHandlerRepository.findById(id);
        if(event.isPresent()) {
            return event.get();
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public void delete(long id) {
        eventHandlerRepository.delete(id);
    }


    public CepEventHandler findByName(String name) {
        Optional<CepEventHandler> event = eventHandlerRepository.findByName(name);
        if(event.isPresent()) {
            return event.get();
        }
        else {
            throw new ResourceNotFoundException();
        }
    }
}
