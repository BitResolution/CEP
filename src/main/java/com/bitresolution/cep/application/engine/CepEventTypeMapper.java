package com.bitresolution.cep.application.engine;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CepEventTypeMapper {

    public Object[] map(CepEvent event, CepEventType eventType) throws ParseException {
        Map json = parseEventContents(event);

        Object[] data = new Object[eventType.getAttributes().size()];
        for(int i = 0; i < eventType.getAttributes().size(); i++) {
            CepEventAttribute attribute = eventType.getAttributes().get(i);
            switch(attribute.getType()) {
                case INT:
                    data[i] = ((Number) json.get(attribute.getName())).intValue();
                    break;

                case LONG:
                    data[i] = ((Number) json.get(attribute.getName())).longValue();
                    break;

                case BOOLEAN:
                    data[i] = ((Boolean) json.get(attribute.getName())).booleanValue();
                    break;

                case FLOAT:
                    data[i] = ((Number) json.get(attribute.getName())).floatValue();
                    break;

                case DOUBLE:
                    data[i] = ((Number) json.get(attribute.getName())).doubleValue();
                    break;

                case STRING:
                    data[i] = (String) json.get(attribute.getName());
                    break;

                default:
                    throw new IllegalStateException("Unexpected AttributeType: " + attribute.getType());
            }
        }
        return data;
    }

    private Map parseEventContents(CepEvent event) throws ParseException {
        JSONParser parser = new JSONParser();
        Map json = (Map) parser.parse(event.getContents(), new ContainerFactory() {
            @Override
            public Map createObjectContainer() {
                return new HashMap();
            }

            @Override
            public List creatArrayContainer() {
                return new ArrayList();
            }
        });
        return json;
    }
}
