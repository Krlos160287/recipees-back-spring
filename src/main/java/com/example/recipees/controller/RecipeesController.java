package com.example.recipees.controller;

import com.example.recipees.dto.RecipeesDTO;
import com.example.recipees.jwt.JwtTokenService;
import com.example.recipees.services.RecipeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipees")
public class RecipeesController {

    @Autowired
    RecipeesService recipeesService;

    @Autowired
    JwtTokenService jwtTokenService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public RecipeesDTO createRecipee(
            @RequestBody RecipeesDTO newRecipee, @RequestHeader("Authorization") String authorizationHeader)
            throws Exception {
        String token = extractTokenFromHeader(authorizationHeader);
        String username = jwtTokenService.getUsernameFromToken(token);
        newRecipee.setCreatedBy(username);

        return recipeesService.createRecipee(newRecipee);
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
