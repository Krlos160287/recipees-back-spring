package com.example.recipees.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeesDTO {
    private String id;
    private String nombre;
    private String steps;
    private String createdBy;
}
