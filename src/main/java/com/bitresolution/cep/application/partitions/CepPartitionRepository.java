package com.bitresolution.cep.application.partitions;

import com.google.common.base.Optional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CepPartitionRepository extends CrudRepository<CepPartition, Long> {

    List<CepPartition> findAll();

    Optional<CepPartition> findById(Long id);

    CepPartition save(CepPartition persisted);

    void delete(Long id);
}
