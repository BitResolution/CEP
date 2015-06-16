package com.bitresolution.cep.web.application;

import com.bitresolution.cep.application.engine.events.CepEvent;
import com.bitresolution.cep.application.engine.events.CepEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/1.0/event")
public class EventController {

    @Autowired
    private CepEventService eventService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<CepEvent>> listEvents() {
        return ResponseEntity.ok(eventService.findAll());
    }

//    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
//    public ResponseEntity<List<CepEvent>> listEvents(@PathVariable int page) {
//        List<CepEvent> events = eventService.list(page * 10, (page * 10) + 10);
//        return ResponseEntity.ok(events);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CepEvent> findEventById(@PathVariable long id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CepEvent> saveEvent(@RequestBody final CepEvent event) {
        event.setReceivedTimestamp(new Date());
        CepEvent persistedEvent = eventService.save(event);
        return ResponseEntity.ok(persistedEvent);
    }
}
