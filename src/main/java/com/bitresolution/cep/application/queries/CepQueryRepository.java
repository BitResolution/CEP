package com.bitresolution.cep.application.queries;

import com.google.common.base.Optional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CepQueryRepository extends CrudRepository<CepQuery, Long> {

    List<CepQuery> findAll();

    Optional<CepQuery> findById(Long id);

    CepQuery save(CepQuery persisted);

    void delete(Long id);
}
