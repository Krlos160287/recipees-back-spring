package com.example.recipees.services.impl;

import com.example.recipees.dto.CategoriesDTO;
import com.example.recipees.models.Categories;
import com.example.recipees.models.Ingredients;
import com.example.recipees.repository.CategoriesRepository;
import com.example.recipees.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<CategoriesDTO> getAllCategories() {
        List<Categories> categories = categoriesRepository.findAll();

        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CategoriesDTO convertToDTO(Categories categories) {
        CategoriesDTO categoriesDTO = new CategoriesDTO();
        categoriesDTO.setCategoria(categories.getCategoria());

        List<Ingredients> ingredients = categories.getIngredientes();
        categoriesDTO.setIngredientes(ingredients);

        return categoriesDTO;
    }
}
