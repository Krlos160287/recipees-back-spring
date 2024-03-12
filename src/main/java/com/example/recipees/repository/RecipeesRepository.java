package com.example.recipees.repository;

import com.example.recipees.models.Recipees;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecipeesRepository extends MongoRepository<Recipees, String> {
    List<Recipees> findByCreatedBy(String createdBy);
}
