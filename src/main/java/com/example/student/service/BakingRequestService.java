package com.example.student.service;

import com.example.student.model.BakingRequest;
import com.example.student.repository.BakingRequestRepository;
import com.example.student.repository.RecipeRepository;
import com.example.student.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BakingRequestService {
    private final BakingRequestRepository repository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public BakingRequestService(BakingRequestRepository repository,
                                RecipeRepository recipeRepository,
                                UserRepository userRepository) {
        this.repository = repository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public List<BakingRequest> getAll() { return repository.findAll(); }

    public BakingRequest getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена: " + id));
    }

    public BakingRequest create(BakingRequest request) {
        if (request.getUserId() == null || !userRepository.existsById(request.getUserId())) {
            throw new RuntimeException("Пользователь не найден: " + request.getUserId());
        }
        if (request.getRecipeId() == null || !recipeRepository.existsById(request.getRecipeId())) {
            throw new RuntimeException("Рецептура не найдена: " + request.getRecipeId());
        }
        request.setId(null);
        if (request.getStatus() == null) request.setStatus("NEW");
        return repository.save(request);
    }

    public BakingRequest update(Long id, BakingRequest patch) {
        BakingRequest existing = getById(id);
        if (patch.getQuantity() != 0) existing.setQuantity(patch.getQuantity());
        if (patch.getStatus() != null) existing.setStatus(patch.getStatus());
        if (patch.getBatchId() != null) existing.setBatchId(patch.getBatchId());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Заявка не найдена: " + id);
        repository.deleteById(id);
    }
}