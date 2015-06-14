package com.bitresolution.cep.application.partitions;

import com.bitresolution.cep.application.rest.ResourceNotFoundException;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CepPartitionService {

    private final CepPartitionRepository repository;

    @Autowired
    public CepPartitionService(CepPartitionRepository repository) {
        this.repository = repository;
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
        return repository.save(cepPartition);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
