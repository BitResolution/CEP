package com.bitresolution.cep.web.application;

import com.bitresolution.cep.application.engine.eventhandlers.StreamConsumerFactory;
import com.bitresolution.cep.application.engine.eventhandlers.StreamConsumerFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/api/1.0")
public class StreamConsumerController {

    private final StreamConsumerFactoryService streamConsumerFactoryService;

    @Autowired
    public StreamConsumerController(StreamConsumerFactoryService streamConsumerFactoryService) {
        this.streamConsumerFactoryService = streamConsumerFactoryService;
    }

    @RequestMapping(value = "/stream-consumer/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StreamConsumerFactory>> listEventHandlers() {
        List<StreamConsumerFactory> eventHandler = streamConsumerFactoryService.findAll();
        return ResponseEntity.ok(eventHandler);
    }
}
