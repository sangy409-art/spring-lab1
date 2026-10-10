package com.example.student.service;

import com.example.student.model.Ingredient;
import com.example.student.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository repository;

    public IngredientService(IngredientRepository repository) {
        this.repository = repository;
    }

    public List<Ingredient> getAll() {
        return repository.findAll();
    }

    public Ingredient getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ингредиент не найден: " + id));
    }

    public Ingredient create(Ingredient ingredient) {
        ingredient.setId(null);
        return repository.save(ingredient);
    }

    public Ingredient update(Long id, Ingredient patch) {
        Ingredient existing = getById(id);
        if (patch.getName() != null) existing.setName(patch.getName());
        if (patch.getUnit() != null) existing.setUnit(patch.getUnit());
        if (patch.getStock() != 0) existing.setStock(patch.getStock());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Ингредиент не найден: " + id);
        }
        repository.deleteById(id);
    }
}