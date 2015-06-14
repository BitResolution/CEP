package com.bitresolution.cep.application.engine;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder(builderMethodName = "cepEventAttribute")
@Entity
public class CepEventAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private AttributeType type;

    public CepEventAttribute(Long id, String name, AttributeType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
