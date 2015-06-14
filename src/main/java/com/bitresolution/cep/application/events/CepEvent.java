package com.bitresolution.cep.application.events;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Builder(builderMethodName = "cepEvent")
@Entity
public class CepEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Class type;

    @Lob
    private String contents;

    @Column
    private Date receivedTimestamp;

    public CepEvent(Long id, Class type, String contents, Date receivedTimestamp) {
        this.id = id;
        this.type = type;
        this.contents = contents;
        this.receivedTimestamp = receivedTimestamp;
    }
}
