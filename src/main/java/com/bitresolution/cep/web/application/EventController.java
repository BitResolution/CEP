package com.bitresolution.cep.web.application;

import com.bitresolution.cep.application.events.CepEvent;
import com.bitresolution.cep.application.events.CepEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/api/1.0/event")
public class EventController {

    private CepEventService eventService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<CepEvent>> listEvents() {
        return listEvents(0);
    }

    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<CepEvent>> listEvents(@PathVariable int page) {
        List<CepEvent> events = eventService.list(page * 10, (page * 10) + 10);
        return ResponseEntity.ok(events);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CepEvent> saveEvent(@PathVariable long id) {
        CepEvent persistedEvent = eventService.findById(id);
        return ResponseEntity.ok(persistedEvent);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CepEvent> saveEvent(@RequestBody final CepEvent event) {
        CepEvent persistedEvent = eventService.save(event);
        return ResponseEntity.ok(persistedEvent);
    }
}
