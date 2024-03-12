package com.example.recipees.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "ingredientes")
public class Categories {
    @Id
    private String id;
    private String categoria;
    private List<Ingredients> ingredientes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
