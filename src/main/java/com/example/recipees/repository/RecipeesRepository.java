package com.example.recipees.repository;

import com.example.recipees.models.Recipees;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeesRepository extends MongoRepository<Recipees, String> {
}
