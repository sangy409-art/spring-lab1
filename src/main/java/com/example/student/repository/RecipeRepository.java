package com.example.student.repository;

import com.example.student.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RecipeRepository {
    private final Map<Long, Recipe> storage = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Recipe> findAll() { return new ArrayList<>(storage.values()); }
    public Optional<Recipe> findById(Long id) { return Optional.ofNullable(storage.get(id)); }
    public Recipe save(Recipe r) {
        if (r.getId() == null) r.setId(counter.getAndIncrement());
        storage.put(r.getId(), r);
        return r;
    }
    public boolean deleteById(Long id) { return storage.remove(id) != null; }
    public boolean existsById(Long id) { return storage.containsKey(id); }
}