package com.bitresolution.cep.application.engine.eventtypes;


import org.wso2.siddhi.query.api.definition.Attribute;

public enum CepEventTypeAttributeType {
    INT(Attribute.Type.INT),
    LONG(Attribute.Type.LONG),
    BOOLEAN(Attribute.Type.BOOL),
    FLOAT(Attribute.Type.FLOAT),
    DOUBLE(Attribute.Type.DOUBLE),
    STRING(Attribute.Type.STRING);

    private final Attribute.Type siddhiType;

    CepEventTypeAttributeType(Attribute.Type siddhiType) {
        this.siddhiType = siddhiType;
    }

    public Attribute.Type siddhiType() {
        return siddhiType;
    }
}
