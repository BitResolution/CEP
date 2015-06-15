package com.bitresolution.cep.application.engine.eventhandlers;

import com.google.common.base.Optional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CepEventHandlerRepository extends CrudRepository<CepEventHandler, Long> {

    List<CepEventHandler> findAll();

    Optional<CepEventHandler> findById(Long id);

    CepEventHandler save(CepEventHandler persisted);

    Optional<CepEventHandler> findByName(String name);
}
