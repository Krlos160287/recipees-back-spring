package com.example.recipees.services;

import com.example.recipees.dto.RecipeesDTO;

import java.text.ParseException;
import java.util.List;

public interface RecipeesService {
    public RecipeesDTO createRecipee(RecipeesDTO newRecipee) throws ParseException;
    public List<RecipeesDTO> getRecipeesByUser(String usermail);
    public RecipeesDTO editRecipee(RecipeesDTO editRecipee) throws ParseException;
}
