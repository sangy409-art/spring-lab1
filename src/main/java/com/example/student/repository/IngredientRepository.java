package com.example.student.repository;

import com.example.student.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class IngredientRepository {
    private final Map<Long, Ingredient> storage = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Ingredient> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Ingredient> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Ingredient save(Ingredient ingredient) {
        if (ingredient.getId() == null) {
            ingredient.setId(counter.getAndIncrement());
        }
        storage.put(ingredient.getId(), ingredient);
        return ingredient;
    }

    public boolean deleteById(Long id) {
        return storage.remove(id) != null;
    }

    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }
}