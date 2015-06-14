package com.bitresolution.cep.application.queries;

import com.bitresolution.cep.application.rest.ResourceNotFoundException;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wso2.siddhi.core.SiddhiManager;

import java.util.List;

@Service
@Transactional
public class CepQueryService {

    private final SiddhiManager siddhiManager;
    private final CepQueryRepository repository;

    @Autowired
    public CepQueryService(CepQueryRepository repository, SiddhiManager siddhiManager) {
        this.siddhiManager = siddhiManager;
        this.repository = repository;
    }

    public List<CepQuery> findAll() {
        return repository.findAll();
    }

    public CepQuery findById(long id) {
        Optional<CepQuery> partition = repository.findById(id);
        if(partition.isPresent()) {
            return partition.get();
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public CepQuery save(CepQuery cepQuery) {
        return repository.save(cepQuery);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
