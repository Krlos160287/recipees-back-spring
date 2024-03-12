package com.example.recipees.controller;

import com.example.recipees.dto.UserDTO;
import com.example.recipees.jwt.JwtAuthenticationResponse;
import com.example.recipees.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.recipees.jwt.JwtTokenService;


@RestController
public class AuthController {
    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDTO user) {
        UserDetails userDetails = authService.authenticate(user.getEmail(), user.getPassword());

        if (userDetails != null) {

            String token = jwtTokenService.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
