package com.bitresolution.cep.application.engine;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder(builderMethodName = "cepEventType")
@Entity
public class CepEventType {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ATTRIBUTE_ID", nullable = false)
    @Singular
    private List<CepEventAttribute> attributes = new ArrayList<CepEventAttribute>();

    public void addAttribute(CepEventAttribute speed) {
        this.attributes.add(speed);
    }

    public CepEventType(Long id, String name, List<CepEventAttribute> attributes) {
        this.id = id;
        this.name = name;
        this.attributes = attributes;
    }
}
