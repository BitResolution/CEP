package com.bitresolution.cep.application.engine.eventtypes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class CepEventTypeAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private CepEventTypeAttributeType type;

    public CepEventTypeAttribute(Long id, String name, CepEventTypeAttributeType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static CepEventTypeAttributeBuilder cepEventAttribute() {
        return new CepEventTypeAttributeBuilder();
    }

    public static class CepEventTypeAttributeBuilder {
        private Long id;
        private String name;
        private CepEventTypeAttributeType type;

        CepEventTypeAttributeBuilder() {
        }

        public CepEventTypeAttribute.CepEventTypeAttributeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CepEventTypeAttribute.CepEventTypeAttributeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CepEventTypeAttribute.CepEventTypeAttributeBuilder type(CepEventTypeAttributeType type) {
            this.type = type;
            return this;
        }

        public CepEventTypeAttribute build() {
            return new CepEventTypeAttribute(id, name, type);
        }

        public String toString() {
            return "com.bitresolution.cep.application.engine.eventtypes.CepEventTypeAttribute.CepEventTypeAttributeBuilder(id=" + this.id + ", name=" + this.name + ", type=" + this.type + ")";
        }
    }
}
