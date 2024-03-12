package com.example.recipees.services;

import com.example.recipees.dto.UserDTO;

import java.text.ParseException;

public interface UserService {
    public UserDTO saveUser(UserDTO newUser) throws ParseException;
}