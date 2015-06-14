package com.bitresolution.cep.application.engine;

import com.bitresolution.cep.application.engine.events.CepEvent;
import com.bitresolution.cep.application.engine.eventtypes.CepEventType;
import com.bitresolution.cep.application.engine.eventtypes.CepEventTypeAttributeType;
import com.bitresolution.cep.application.engine.eventtypes.CepEventTypeMapper;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.util.Date;

import static com.bitresolution.cep.application.engine.events.CepEvent.cepEvent;
import static com.bitresolution.cep.application.engine.eventtypes.CepEventTypeAttribute.cepEventAttribute;
import static com.bitresolution.cep.application.engine.eventtypes.CepEventType.cepEventType;
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
                        cepEventAttribute().name("intType").type(CepEventTypeAttributeType.INT).build(),
                        cepEventAttribute().name("longType").type(CepEventTypeAttributeType.LONG).build(),
                        cepEventAttribute().name("doubleType").type(CepEventTypeAttributeType.DOUBLE).build(),
                        cepEventAttribute().name("floatType").type(CepEventTypeAttributeType.FLOAT).build(),
                        cepEventAttribute().name("stringType").type(CepEventTypeAttributeType.STRING).build(),
                        cepEventAttribute().name("booleanType").type(CepEventTypeAttributeType.BOOLEAN).build()
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