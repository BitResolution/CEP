package com.bitresolution.cep.application.engine;


import org.wso2.siddhi.query.api.definition.Attribute;

public enum AttributeType {
    INT(Attribute.Type.INT),
    LONG(Attribute.Type.LONG),
    BOOLEAN(Attribute.Type.BOOL),
    FLOAT(Attribute.Type.FLOAT),
    DOUBLE(Attribute.Type.DOUBLE),
    STRING(Attribute.Type.STRING);

    private final Attribute.Type siddhiType;

    AttributeType(Attribute.Type siddhiType) {
        this.siddhiType = siddhiType;
    }

    public Attribute.Type siddhiType() {
        return siddhiType;
    }
}
