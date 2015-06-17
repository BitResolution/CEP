package com.bitresolution.cep.application.queries;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
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

    public static CepQueryBuilder cepQuery() {
        return new CepQueryBuilder();
    }

    public static class CepQueryBuilder {
        private Long id;
        private String name;
        private String definition;

        CepQueryBuilder() {
        }

        public CepQuery.CepQueryBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CepQuery.CepQueryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CepQuery.CepQueryBuilder definition(String definition) {
            this.definition = definition;
            return this;
        }

        public CepQuery build() {
            return new CepQuery(id, name, definition);
        }

        public String toString() {
            return "com.bitresolution.cep.application.queries.CepQuery.CepQueryBuilder(id=" + this.id + ", name=" + this.name + ", definition=" + this.definition + ")";
        }
    }
}
