package com.bitresolution.cep.application.queries;

import com.bitresolution.cep.application.engine.CepEngine;
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
public class CepQueryService {

    private final CepQueryRepository repository;
    private final CepEngine engine;

    @Autowired
    public CepQueryService(CepQueryRepository repository, CepEngine engine) {
        this.repository = repository;
        this.engine = engine;
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
        boolean isUpdate = Strings.isNullOrEmpty(cepQuery.getName());
        CepQuery persistedQuery = repository.save(cepQuery);
        if(isUpdate) {
            engine.updateQuery(persistedQuery);
        }
        else {
            engine.addQuery(persistedQuery);
        }
        return persistedQuery;
    }

    public void delete(long id) {
        CepQuery query = findById(id);
        repository.delete(id);
        engine.deleteQuery(query);
    }
}
