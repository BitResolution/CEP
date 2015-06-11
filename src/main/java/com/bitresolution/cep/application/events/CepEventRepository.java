package com.bitresolution.cep.application.events;

import com.google.common.base.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CepEventRepository extends CrudRepository<CepEvent, Long> {

    List<CepEvent> findAll();

    Optional<CepEvent> findById(Long id);

    CepEvent save(CepEvent persisted);

    @Query("SELECT e FROM CepEvent e ORDER BY e.receivedTimestamp")
    List<CepEvent> findAllByReceivedTimestamp(Pageable pageable);
}
