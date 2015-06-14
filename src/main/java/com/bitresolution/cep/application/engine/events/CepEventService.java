package com.bitresolution.cep.application.engine.events;

import com.bitresolution.cep.application.rest.ResourceNotFoundException;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class CepEventService {

    private final CepEventRepository repository;
    private final CepEventPublisher publisher;

    @Autowired
    public CepEventService(CepEventRepository repository, CepEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public List<CepEvent> findAll() {
        return repository.findAll();
    }

    public CepEvent save(CepEvent event) {
        CepEvent cepEvent = repository.save(event);
        publisher.publish(cepEvent);
        return cepEvent;
    }

    public CepEvent findById(long id) {
        Optional<CepEvent> event = repository.findById(id);
        if(event.isPresent()) {
            return event.get();
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public List<CepEvent> list(int offset, int count) {
        return repository.findAllByReceivedTimestamp(new PageRequest(offset, count));
    }
}
