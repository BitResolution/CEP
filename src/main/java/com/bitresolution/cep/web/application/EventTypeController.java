package com.bitresolution.cep.web.application;

import com.bitresolution.cep.application.engine.CepEventType;
import com.bitresolution.cep.application.engine.CepEventTypeService;
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
public class EventTypeController {

    private final CepEventTypeService eventTypeService;

    @Autowired
    public EventTypeController(CepEventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @RequestMapping(value = "/event-type/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CepEventType>> listEvents() {
        List<CepEventType> eventTypes = eventTypeService.findAll();
        return ResponseEntity.ok(eventTypes);
    }

    @RequestMapping(value = "/event-type", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CepEventType> saveEvent(@RequestBody final CepEventType event) {
        CepEventType persistedEvent = eventTypeService.save(event);
        return ResponseEntity.ok(persistedEvent);
    }

    @RequestMapping(value = "/event-type/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CepEventType> getEvent(@PathVariable long id) {
        CepEventType persistedEvent = eventTypeService.findById(id);
        return ResponseEntity.ok(persistedEvent);
    }

    @RequestMapping(value = "/event-type/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CepEventType> updateEvent(@RequestBody final CepEventType event) {
        CepEventType persistedEvent = eventTypeService.save(event);
        return ResponseEntity.ok(persistedEvent);
    }

    @RequestMapping(value = "/event-type/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteEvent(@PathVariable long id) {
        eventTypeService.delete(id);
        return ResponseEntity.ok(null);
    }
}
