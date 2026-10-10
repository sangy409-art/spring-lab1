package com.example.student.controller;

import com.example.student.model.ProductionBatch;
import com.example.student.service.ProductionBatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production-batches")
public class ProductionBatchController {
    private final ProductionBatchService service;

    public ProductionBatchController(ProductionBatchService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<ProductionBatch>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionBatch> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductionBatch> create(@RequestBody ProductionBatch batch) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(batch));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductionBatch> update(@PathVariable Long id, @RequestBody ProductionBatch patch) {
        return ResponseEntity.ok(service.update(id, patch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}