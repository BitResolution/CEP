package com.bitresolution.cep.application.cep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wso2.siddhi.core.SiddhiManager;

@Service
public class StreamService {

    private final SiddhiManager siddhiManager;

    @Autowired
    public StreamService(SiddhiManager siddhiManager) {
        this.siddhiManager = siddhiManager;
    }
}