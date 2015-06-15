package com.bitresolution.cep.web.application;

import com.bitresolution.cep.application.engine.eventhandlers.CepEventHandler;
import com.bitresolution.cep.application.engine.eventhandlers.CepEventHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/api/1.0")
public class EventHandlerController {

    private final CepEventHandlerService eventHandlerService;

    @Autowired
    public EventHandlerController(CepEventHandlerService eventHandlerService) {
        this.eventHandlerService = eventHandlerService;
    }

    @RequestMapping(value = "/event-handler/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CepEventHandler>> listEventHandlers() {
        List<CepEventHandler> eventHandler = eventHandlerService.findAll();
        return ResponseEntity.ok(eventHandler);
    }

    @RequestMapping(value = "/event-handler", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CepEventHandler> saveEventHandler(@RequestBody final CepEventHandler eventHandler) {
        CepEventHandler persistedEventHandler = eventHandlerService.save(eventHandler);
        return ResponseEntity.ok(persistedEventHandler);
    }

    @RequestMapping(value = "/event-handler/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CepEventHandler> getEventHandler(@PathVariable long id) {
        CepEventHandler persistedEventHandler = eventHandlerService.findById(id);
        return ResponseEntity.ok(persistedEventHandler);
    }

    @RequestMapping(value = "/event-handler/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CepEventHandler> updateEventHandler(@RequestBody final CepEventHandler eventHandler) {
        CepEventHandler persistedEventHandler = eventHandlerService.save(eventHandler);
        return ResponseEntity.ok(persistedEventHandler);
    }

    @RequestMapping(value = "/event-handler/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteEventHandler(@PathVariable long id) {
        eventHandlerService.delete(id);
        return ResponseEntity.ok(null);
    }
}
