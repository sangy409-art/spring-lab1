package com.example.student.model;

public class ProductionBatch {
    private Long id;
    private Long recipeId;
    private int quantity;
    private String status; // PLANNED, IN_PROGRESS, DONE

    public ProductionBatch() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRecipeId() { return recipeId; }
    public void setRecipeId(Long recipeId) { this.recipeId = recipeId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}