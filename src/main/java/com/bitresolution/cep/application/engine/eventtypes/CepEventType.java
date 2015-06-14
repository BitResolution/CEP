package com.bitresolution.cep.application.engine.eventtypes;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
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
    private List<CepEventTypeAttribute> attributes = new ArrayList<CepEventTypeAttribute>();

    public static CepEventTypeBuilder cepEventType() {
        return new CepEventTypeBuilder();
    }

    public void addAttribute(CepEventTypeAttribute speed) {
        this.attributes.add(speed);
    }

    public CepEventType(Long id, String name, List<CepEventTypeAttribute> attributes) {
        this.id = id;
        this.name = name;
        this.attributes = attributes;
    }

    public static class CepEventTypeBuilder {
        private Long id;
        private String name;
        private List<CepEventTypeAttribute> attributes;

        CepEventTypeBuilder() {
        }

        public CepEventType.CepEventTypeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CepEventType.CepEventTypeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CepEventType.CepEventTypeBuilder attributes(List<CepEventTypeAttribute> attributes) {
            this.attributes = attributes;
            return this;
        }

        public CepEventType build() {
            return new CepEventType(id, name, attributes);
        }

        public String toString() {
            return "com.bitresolution.cep.application.engine.eventtypes.CepEventType.CepEventTypeBuilder(id=" + this.id + ", name=" + this.name + ", attributes=" + this.attributes + ")";
        }
    }
}
