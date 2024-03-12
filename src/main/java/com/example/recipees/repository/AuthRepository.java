package com.example.recipees.repository;

import com.example.recipees.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
