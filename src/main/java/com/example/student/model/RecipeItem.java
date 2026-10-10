package com.example.student.model;

public class RecipeItem {
    private Long ingredientId;
    private double quantity;

    public RecipeItem() {}

    public RecipeItem(Long ingredientId, double quantity) {
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }

    public Long getIngredientId() { return ingredientId; }
    public void setIngredientId(Long ingredientId) { this.ingredientId = ingredientId; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }
}