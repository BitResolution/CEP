package com.bitresolution.cep.web.application;

import com.bitresolution.cep.application.queries.CepQuery;
import com.bitresolution.cep.application.queries.CepQueryService;
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
public class QueryController {

    private final CepQueryService queryService;

    @Autowired
    public QueryController(CepQueryService queryService) {
        this.queryService = queryService;
    }

    @RequestMapping(value = "/query/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CepQuery>> listQueries() {
        List<CepQuery> query = queryService.findAll();
        return ResponseEntity.ok(query);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CepQuery> saveQuery(@RequestBody final CepQuery query) {
        CepQuery persistedQuery = queryService.save(query);
        return ResponseEntity.ok(persistedQuery);
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CepQuery> getQuery(@PathVariable long id) {
        CepQuery persistedQuery = queryService.findById(id);
        return ResponseEntity.ok(persistedQuery);
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CepQuery> updateQuery(@RequestBody final CepQuery query) {
        CepQuery persistedQuery = queryService.save(query);
        return ResponseEntity.ok(persistedQuery);
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deleteQuery(@PathVariable long id) {
        queryService.delete(id);
        return ResponseEntity.ok(null);
    }
}
