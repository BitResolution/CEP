package com.bitresolution.cep.application.engine.eventhandlers;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder(builderMethodName = "cepEvent")
@Entity
public class CepEventHandler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String streamName;

    @Column
    private Class<? extends StreamConsumer> streamConsumer;

    public CepEventHandler(Long id, String name, String description, String streamName, Class<? extends StreamConsumer> streamConsumer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.streamName = streamName;
        this.streamConsumer = streamConsumer;
    }
}
