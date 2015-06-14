package com.bitresolution.cep.web.application;

import com.bitresolution.cep.application.partitions.CepPartition;
import com.bitresolution.cep.application.partitions.CepPartitionService;
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
public class PartitionController {

    private final CepPartitionService partitionService;

    @Autowired
    public PartitionController(CepPartitionService partitionService) {
        this.partitionService = partitionService;
    }

    @RequestMapping(value = "/partition/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CepPartition>> listPartitions() {
        List<CepPartition> partition = partitionService.findAll();
        return ResponseEntity.ok(partition);
    }

    @RequestMapping(value = "/partition", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CepPartition> savePartition(@RequestBody final CepPartition partition) {
        CepPartition persistedPartition = partitionService.save(partition);
        return ResponseEntity.ok(persistedPartition);
    }

    @RequestMapping(value = "/partition/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CepPartition> getPartition(@PathVariable long id) {
        CepPartition persistedPartition = partitionService.findById(id);
        return ResponseEntity.ok(persistedPartition);
    }

    @RequestMapping(value = "/partition/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CepPartition> updatePartition(@RequestBody final CepPartition partition) {
        CepPartition persistedPartition = partitionService.save(partition);
        return ResponseEntity.ok(persistedPartition);
    }

    @RequestMapping(value = "/partition/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> deletePartition(@PathVariable long id) {
        partitionService.delete(id);
        return ResponseEntity.ok(null);
    }
}
