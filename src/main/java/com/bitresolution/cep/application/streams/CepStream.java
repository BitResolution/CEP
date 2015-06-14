package com.bitresolution.cep.application.streams;


import com.bitresolution.cep.application.engine.eventtypes.CepEventType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder(builderMethodName = "cepStream")
@Entity
public class CepStream {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENTTYPE_ID", nullable = false)
    private CepEventType eventType;

    public CepStream(Long id, String name, CepEventType type) {
        this.id = id;
        this.name = name;
        this.eventType = type;
    }
}
