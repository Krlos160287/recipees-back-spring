package com.example.recipees.services;

import com.example.recipees.models.User;
import com.example.recipees.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDetails authenticate(String email, String password) {

        User user = authRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
