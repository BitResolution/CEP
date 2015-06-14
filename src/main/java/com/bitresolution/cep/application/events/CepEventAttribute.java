package com.bitresolution.cep.application.events;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
