package com.bitresolution.cep.application.engine;

import com.bitresolution.cep.application.engine.events.CepEvent;
import com.bitresolution.cep.application.engine.eventtypes.CepEventType;
import com.bitresolution.cep.application.engine.eventtypes.CepEventTypeAttributeType;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.wso2.siddhi.core.stream.input.InputHandler;

import java.util.Date;

import static com.bitresolution.cep.application.engine.events.CepEvent.cepEvent;
import static com.bitresolution.cep.application.engine.eventtypes.CepEventTypeAttribute.cepEventAttribute;
import static com.bitresolution.cep.application.engine.eventtypes.CepEventType.cepEventType;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CepInputHandlerAdapterTest {

    @Mock
    private InputHandler inputHandler;

    private CepEventType eventType;


    CepInputHandlerAdapter adapter;

    @Before
    public void setUp() throws Exception {
        eventType = CepEventType.cepEventType().name("EventTypeA").build();

        adapter = new CepInputHandlerAdapter(inputHandler, eventType);
    }

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
        Object[] actual = adapter.createDataObject(event, eventType);

        //then
        Object[] expected = {9, 9L, 9D, 9.9F, "fish", true};
        assertThat(actual).isEqualTo(expected);
    }


}