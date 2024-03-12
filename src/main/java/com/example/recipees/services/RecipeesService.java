package com.example.recipees.services;

import com.example.recipees.dto.RecipeesDTO;

import java.text.ParseException;

public interface RecipeesService {
    public RecipeesDTO createRecipee(RecipeesDTO newRecipee) throws ParseException;
}
