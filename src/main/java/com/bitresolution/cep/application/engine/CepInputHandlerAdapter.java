package com.bitresolution.cep.application.engine;

import org.json.simple.parser.ParseException;
import org.wso2.siddhi.core.stream.input.InputHandler;

public class CepInputHandlerAdapter {

    private final InputHandler inputHandler;
    private final CepEventType eventType;
    private final CepEventTypeMapper mapper;

    public CepInputHandlerAdapter(InputHandler inputHandler, CepEventType eventType) {
        this(inputHandler, eventType, new CepEventTypeMapper());
    }

    public CepInputHandlerAdapter(InputHandler inputHandler, CepEventType eventType, CepEventTypeMapper mapper) {
        this.inputHandler = inputHandler;
        this.eventType = eventType;
        this.mapper = mapper;
    }

    public void send(CepEvent event) {
        try {
            inputHandler.send(event.getReceivedTimestamp().getTime(), mapper.map(event, eventType));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
