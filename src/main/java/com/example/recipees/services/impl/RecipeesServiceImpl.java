package com.example.recipees.services.impl;

import com.example.recipees.dto.RecipeesDTO;
import com.example.recipees.models.Recipees;
import com.example.recipees.repository.RecipeesRepository;
import com.example.recipees.services.RecipeesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeesServiceImpl implements RecipeesService {
    @Autowired
    RecipeesRepository recipeesRepository;

    @Override
    public RecipeesDTO createRecipee(RecipeesDTO newRecipee) {
        Recipees recipees = new Recipees();

        BeanUtils.copyProperties(newRecipee, recipees);

        Recipees savedRecipee = recipeesRepository.save(recipees);

        RecipeesDTO createdRecipeeDTO = new RecipeesDTO();
        BeanUtils.copyProperties(savedRecipee, createdRecipeeDTO);

        return createdRecipeeDTO;
    }
}
