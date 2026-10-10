package com.example.student.service;

import com.example.student.model.Recipe;
import com.example.student.repository.BakingRequestRepository;
import com.example.student.repository.ProductionBatchRepository;
import com.example.student.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository repository;
    private final ProductionBatchRepository batchRepository;
    private final BakingRequestRepository requestRepository;

    public RecipeService(RecipeRepository repository,
                         ProductionBatchRepository batchRepository,
                         BakingRequestRepository requestRepository) {
        this.repository = repository;
        this.batchRepository = batchRepository;
        this.requestRepository = requestRepository;
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
        if (!repository.existsById(id)) {
            throw new RuntimeException("Рецептура не найдена: " + id);
        }
        // Проверяем, используется ли рецептура в производственных партиях
        boolean usedInBatch = batchRepository.findAll().stream()
                .anyMatch(b -> b.getRecipeId() != null && b.getRecipeId().equals(id));
        if (usedInBatch) {
            throw new RuntimeException(
                    "Нельзя удалить рецептуру id=" + id + ": она используется в производственных партиях");
        }
        // Проверяем, используется ли рецептура в заявках
        boolean usedInRequest = requestRepository.findAll().stream()
                .anyMatch(r -> r.getRecipeId() != null && r.getRecipeId().equals(id));
        if (usedInRequest) {
            throw new RuntimeException(
                    "Нельзя удалить рецептуру id=" + id + ": она используется в заявках на выпечку");
        }
        repository.deleteById(id);
    }
}