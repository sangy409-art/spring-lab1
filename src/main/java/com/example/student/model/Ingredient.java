package com.example.student.model;

public class Ingredient {
    private Long id;
    private String name;
    private String unit;
    private double stock;

    public Ingredient() {}

    public Ingredient(Long id, String name, String unit, double stock) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.stock = stock;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public double getStock() { return stock; }
    public void setStock(double stock) { this.stock = stock; }
}