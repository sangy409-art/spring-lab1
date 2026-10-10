package com.example.student.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private Long id;
    private String name;
    private List<RecipeItem> items = new ArrayList<>();

    public Recipe() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<RecipeItem> getItems() { return items; }
    public void setItems(List<RecipeItem> items) { this.items = items; }
}