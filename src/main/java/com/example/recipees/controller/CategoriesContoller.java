package com.example.recipees.controller;

import com.example.recipees.dto.CategoriesDTO;
import com.example.recipees.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class CategoriesContoller {

    @Autowired
    CategoriesService categoriesService;
    @GetMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CategoriesDTO> getAllCategories() {
        return categoriesService.getAllCategories();
    }
}
