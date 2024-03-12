package com.example.recipees.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredients {
    private String id;
    private String nombre;

    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
