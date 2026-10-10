package com.example.student.repository;

import com.example.student.model.ProductionBatch;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductionBatchRepository {
    private final Map<Long, ProductionBatch> storage = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<ProductionBatch> findAll() { return new ArrayList<>(storage.values()); }
    public Optional<ProductionBatch> findById(Long id) { return Optional.ofNullable(storage.get(id)); }
    public ProductionBatch save(ProductionBatch b) {
        if (b.getId() == null) b.setId(counter.getAndIncrement());
        storage.put(b.getId(), b);
        return b;
    }
    public boolean deleteById(Long id) { return storage.remove(id) != null; }
    public boolean existsById(Long id) { return storage.containsKey(id); }
}