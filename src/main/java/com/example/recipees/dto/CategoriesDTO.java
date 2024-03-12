package com.example.recipees.dto;

import com.example.recipees.models.Ingredients;

import java.util.List;

public class CategoriesDTO {

    private String categoria;
    private List<Ingredients> ingredientes;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Ingredients> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingredients> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
