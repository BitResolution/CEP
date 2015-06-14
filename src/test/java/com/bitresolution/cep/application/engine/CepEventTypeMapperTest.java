package com.bitresolution.cep.application.engine;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.util.Date;

import static com.bitresolution.cep.application.engine.CepEvent.cepEvent;
import static com.bitresolution.cep.application.engine.CepEventAttribute.cepEventAttribute;
import static com.bitresolution.cep.application.engine.CepEventType.cepEventType;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CepEventTypeMapperTest {

    CepEventTypeMapper mapper = new CepEventTypeMapper();

    @Test
    public void shouldMapAllJsonTypes() throws ParseException {
        //given

        CepEventType eventType = cepEventType()
                .name("AllTypesEvent")
                .attributes(asList(
                        cepEventAttribute().name("intType").type(AttributeType.INT).build(),
                        cepEventAttribute().name("longType").type(AttributeType.LONG).build(),
                        cepEventAttribute().name("doubleType").type(AttributeType.DOUBLE).build(),
                        cepEventAttribute().name("floatType").type(AttributeType.FLOAT).build(),
                        cepEventAttribute().name("stringType").type(AttributeType.STRING).build(),
                        cepEventAttribute().name("booleanType").type(AttributeType.BOOLEAN).build()
                )).build();

        CepEvent event = cepEvent().type("AllTypesEvent").receivedTimestamp(new Date()).contents("{" +
                "\"intType\": 9," +
                "\"longType\": 9," +
                "\"doubleType\": 9," +
                "\"floatType\": 9.9," +
                "\"stringType\": \"fish\"," +
                "\"booleanType\": true" +
                "}").build();

        //when
        Object[] actual = mapper.map(event, eventType);

        //then
        Object[] expected = {9, 9L, 9D, 9.9F, "fish", true};
        assertThat(actual).isEqualTo(expected);
    }


}