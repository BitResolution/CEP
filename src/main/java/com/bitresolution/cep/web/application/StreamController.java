package com.bitresolution.cep.web.application;

import com.bitresolution.cep.application.streams.CepStream;
import com.bitresolution.cep.application.streams.CepStreamService;
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
public class StreamController {

    private final CepStreamService streamService;

    @Autowired
    public StreamController(CepStreamService streamService) {
        this.streamService = streamService;
    }

    @RequestMapping(value = "/stream/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CepStream>> listEvents() {
        List<CepStream> eventTypes = streamService.findAll();
        return ResponseEntity.ok(eventTypes);
    }

    @RequestMapping(value = "/stream", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CepStream> saveEvent(@RequestBody final CepStream event) {
        CepStream persistedEvent = streamService.save(event);
        return ResponseEntity.ok(persistedEvent);
    }

    @RequestMapping(value = "/stream/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CepStream> getEvent(@PathVariable long id) {
        CepStream persistedEvent = streamService.findById(id);
        return ResponseEntity.ok(persistedEvent);
    }

    @RequestMapping(value = "/stream/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CepStream> updateEvent(@RequestBody final CepStream event) {
        CepStream persistedEvent = streamService.save(event);
        return ResponseEntity.ok(persistedEvent);
    }

    @RequestMapping(value = "/stream/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteEvent(@PathVariable long id) {
        streamService.delete(id);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/stream/system", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<String>> listSystemEvents() {
        List<String> eventTypes = streamService.findAllSystemStreams();
        return ResponseEntity.ok(eventTypes);
    }
}
