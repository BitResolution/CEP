package com.bitresolution.cep.web.application;

import com.bitresolution.cep.application.events.CepEvent;
import com.bitresolution.cep.application.events.CepEventType;
import com.bitresolution.cep.application.events.CepEventTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/api/1.0/event-type")
public class EventTypeController {

    private CepEventTypeService eventTypeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<CepEventType>> listEvents() {
        List<CepEventType> eventTypes = eventTypeService.findAll();
        return ResponseEntity.ok(eventTypes);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CepEventType> saveEvent(@PathVariable long id) {
        CepEventType persistedEvent = eventTypeService.findById(id);
        return ResponseEntity.ok(persistedEvent);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CepEventType> saveEvent(@RequestBody final CepEventType event) {
        CepEventType persistedEvent = eventTypeService.save(event);
        return ResponseEntity.ok(persistedEvent);
    }
}
