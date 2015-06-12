package com.bitresolution.cep.application.events;

import com.google.common.base.Optional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CepEventTypeRepository  extends CrudRepository<CepEventType, Long> {

    List<CepEventType> findAll();

    Optional<CepEventType> findById(Long id);

    CepEventType save(CepEventType persisted);
}
