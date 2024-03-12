package com.example.recipees.dto;

import com.example.recipees.models.Products;

import java.util.List;

public class RecipeesDTO {
    private String nombre;
    private List<Products> products;
    private String steps;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
