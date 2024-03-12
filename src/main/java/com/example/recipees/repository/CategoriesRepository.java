package com.example.recipees.repository;

import com.example.recipees.models.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoriesRepository extends MongoRepository<Categories, Long> {
    List<Categories> findAll();
}
