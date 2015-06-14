package com.bitresolution.cep.application.partitions;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder(builderMethodName = "cepPartition")
@Entity
public class CepPartition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String definition;

    public CepPartition(Long id, String name, String definition) {
        this.id = id;
        this.name = name;
        this.definition = definition;
    }
}
