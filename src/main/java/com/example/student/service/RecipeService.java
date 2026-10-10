package com.example.student.service;

import com.example.student.model.Recipe;
import com.example.student.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public List<Recipe> getAll() { return repository.findAll(); }

    public Recipe getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Рецептура не найдена: " + id));
    }

    public Recipe create(Recipe recipe) {
        recipe.setId(null);
        return repository.save(recipe);
    }

    public Recipe update(Long id, Recipe patch) {
        Recipe existing = getById(id);
        if (patch.getName() != null) existing.setName(patch.getName());
        if (patch.getItems() != null) existing.setItems(patch.getItems());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Рецептура не найдена: " + id);
        repository.deleteById(id);
    }
}