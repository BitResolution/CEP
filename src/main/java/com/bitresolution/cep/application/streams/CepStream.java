package com.bitresolution.cep.application.streams;


import com.bitresolution.cep.application.engine.eventtypes.CepEventType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
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

    public static CepStreamBuilder cepStream() {
        return new CepStreamBuilder();
    }

    public static class CepStreamBuilder {
        private Long id;
        private String name;
        private CepEventType eventType;

        CepStreamBuilder() {
        }

        public CepStream.CepStreamBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CepStream.CepStreamBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CepStream.CepStreamBuilder eventType(CepEventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public CepStream build() {
            return new CepStream(id, name, eventType);
        }

        public String toString() {
            return "com.bitresolution.cep.application.streams.CepStream.CepStreamBuilder(id=" + this.id + ", name=" + this.name + ", eventType=" + this.eventType + ")";
        }
    }
}
