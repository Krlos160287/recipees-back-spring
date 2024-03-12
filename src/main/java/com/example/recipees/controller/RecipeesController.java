package com.example.recipees.controller;

import com.example.recipees.dto.RecipeesDTO;
import com.example.recipees.services.RecipeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipees")
public class RecipeesController {

    @Autowired
    RecipeesService recipeesService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public RecipeesDTO createRecipee(@RequestBody RecipeesDTO newRecipee) throws Exception {
        return recipeesService.createRecipee(newRecipee);
    }
}
