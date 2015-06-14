package com.bitresolution.cep.application.events;

import com.bitresolution.cep.application.rest.ResourceCanNotBeDeletedException;
import com.bitresolution.cep.application.rest.ResourceNotFoundException;
import com.bitresolution.cep.application.streams.CepStream;
import com.bitresolution.cep.application.streams.CepStreamService;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class CepEventTypeService {

    private final CepEventTypeRepository eventTypeRepository;
    private final CepStreamService streamService;

    @Autowired
    public CepEventTypeService(CepEventTypeRepository eventTypeRepository, CepStreamService streamService) {
        this.eventTypeRepository = eventTypeRepository;
        this.streamService = streamService;
    }

    public List<CepEventType> findAll() {
        return eventTypeRepository.findAll();
    }

    public CepEventType save(CepEventType event) {
        return eventTypeRepository.save(event);
    }

    public CepEventType findById(long id) {
        Optional<CepEventType> event = eventTypeRepository.findById(id);
        if(event.isPresent()) {
            return event.get();
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public void delete(long id) {
        CepEventType eventType = findById(id);
        if(streamService.findByCepEventType(eventType).size() > 0){
            throw new ResourceCanNotBeDeletedException();
        }
        eventTypeRepository.delete(id);
    }
}
