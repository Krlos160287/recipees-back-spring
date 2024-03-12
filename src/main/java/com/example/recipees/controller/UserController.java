package com.example.recipees.controller;

import com.example.recipees.dto.UserDTO;
import com.example.recipees.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDTO creaUser(@RequestBody UserDTO newUser) throws ParseException {
        return userService.saveUser(newUser);
    }
}
