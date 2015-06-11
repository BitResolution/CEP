package com.bitresolution.cep.application.events;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class CepEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Class<?> type;

    @Lob
    private String contents;

    private Date receivedTimestamp;
}
