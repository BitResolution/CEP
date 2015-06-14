package com.bitresolution.cep.application.partitions;

import com.bitresolution.cep.application.engine.CepEngine;
import com.bitresolution.cep.application.rest.ResourceNotFoundException;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CepPartitionService {

    private final CepPartitionRepository repository;
    private final CepEngine engine;

    @Autowired
    public CepPartitionService(CepPartitionRepository repository, CepEngine engine) {
        this.repository = repository;
        this.engine = engine;
    }

    public List<CepPartition> findAll() {
        return repository.findAll();
    }

    public CepPartition findById(long id) {
        Optional<CepPartition> partition = repository.findById(id);
        if(partition.isPresent()) {
            return partition.get();
        }
        else {
            throw new ResourceNotFoundException();
        }
    }

    public CepPartition save(CepPartition cepPartition) {
        boolean isUpdate = Strings.isNullOrEmpty(cepPartition.getName());
        CepPartition persistedPartition = repository.save(cepPartition);
        if(isUpdate) {
            engine.updatePartition(persistedPartition);
        }
        else {
            engine.addPartition(persistedPartition);
        }
        return persistedPartition;
    }

    public void delete(long id) {
        CepPartition partition = findById(id);
        repository.delete(id);
        engine.deletePartition(partition);
    }
}
