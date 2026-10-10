package com.example.student.service;

import com.example.student.model.ProductionBatch;
import com.example.student.repository.ProductionBatchRepository;
import com.example.student.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionBatchService {
    private final ProductionBatchRepository repository;
    private final RecipeRepository recipeRepository;

    public ProductionBatchService(ProductionBatchRepository repository,
                                  RecipeRepository recipeRepository) {
        this.repository = repository;
        this.recipeRepository = recipeRepository;
    }

    public List<ProductionBatch> getAll() { return repository.findAll(); }

    public ProductionBatch getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Партия не найдена: " + id));
    }

    public ProductionBatch create(ProductionBatch batch) {
        if (batch.getRecipeId() == null || !recipeRepository.existsById(batch.getRecipeId())) {
            throw new RuntimeException("Рецептура не найдена: " + batch.getRecipeId());
        }
        batch.setId(null);
        if (batch.getStatus() == null) batch.setStatus("PLANNED");
        return repository.save(batch);
    }

    public ProductionBatch update(Long id, ProductionBatch patch) {
        ProductionBatch existing = getById(id);
        if (patch.getQuantity() != 0) existing.setQuantity(patch.getQuantity());
        if (patch.getStatus() != null) existing.setStatus(patch.getStatus());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Партия не найдена: " + id);
        repository.deleteById(id);
    }
}