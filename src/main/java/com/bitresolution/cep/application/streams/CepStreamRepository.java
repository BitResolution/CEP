package com.bitresolution.cep.application.streams;

import com.bitresolution.cep.application.engine.CepEventType;
import com.google.common.base.Optional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CepStreamRepository extends CrudRepository<CepStream, Long> {

    List<CepStream> findAll();

    Optional<CepStream> findById(Long id);

    CepStream save(CepStream persisted);

    List<CepStream> findByEventType(CepEventType eventType);
}
