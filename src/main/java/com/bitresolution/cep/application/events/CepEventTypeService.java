package com.bitresolution.cep.application.events;

import com.bitresolution.cep.application.rest.ResourceNotFoundException;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class CepEventTypeService {

    private final CepEventTypeRepository repository;

    @Autowired
    public CepEventTypeService(CepEventTypeRepository repository) {
        this.repository = repository;
    }

    public List<CepEventType> findAll() {
        return repository.findAll();
    }

    public CepEventType save(CepEventType event) {
        return repository.save(event);
    }

    public CepEventType findById(long id) {
        Optional<CepEventType> event = repository.findById(id);
        if(event.isPresent()) {
            return event.get();
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
