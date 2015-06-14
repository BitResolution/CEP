package com.bitresolution.cep.application.queries;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder(builderMethodName = "cepQuery")
@Entity
public class CepQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String definition;

    public CepQuery(Long id, String name, String definition) {
        this.id = id;
        this.name = name;
        this.definition = definition;
    }
}
