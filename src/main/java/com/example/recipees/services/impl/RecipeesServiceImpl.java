package com.example.recipees.services.impl;

import com.example.recipees.dto.RecipeesDTO;
import com.example.recipees.models.Recipees;
import com.example.recipees.repository.RecipeesRepository;
import com.example.recipees.services.RecipeesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<RecipeesDTO> getRecipeesByUser(String usermail) {
        List<RecipeesDTO> recipeesDTO = new ArrayList<>();
        List<Recipees> recipees = recipeesRepository.findByCreatedBy(usermail);

        for (Recipees recipe : recipees) {
            RecipeesDTO recipeesDto = new RecipeesDTO();
            BeanUtils.copyProperties(recipe, recipeesDto);
            recipeesDto.setId(recipe.getId());
            recipeesDTO.add(recipeesDto);
        }

        return recipeesDTO;
    }

    @Override
    public RecipeesDTO editRecipee(RecipeesDTO editRecipee) {
        Optional<Recipees> optionalRecipe = recipeesRepository.findById(editRecipee.getId());
        if (optionalRecipe.isEmpty()) {
            return null;
        }
        Recipees existingRecipe = optionalRecipe.get();

        existingRecipe.setNombre(editRecipee.getNombre());
        existingRecipe.setProducts(editRecipee.getProducts());
        existingRecipe.setSteps(editRecipee.getSteps());
        existingRecipe.setCreatedBy(editRecipee.getCreatedBy());

        Recipees savedRecipe = recipeesRepository.save(existingRecipe);

        RecipeesDTO updatedRecipeDTO = new RecipeesDTO();
        BeanUtils.copyProperties(savedRecipe, updatedRecipeDTO);
        return updatedRecipeDTO;
    }
}
