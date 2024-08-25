package com.example.recipees.services.impl;

import com.example.recipees.dto.UserDTO;
import com.example.recipees.models.User;
import com.example.recipees.repository.UserRepository;
import com.example.recipees.services.UserService;
import com.example.recipees.util.Constants;
import com.example.recipees.util.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDTO saveUser(UserDTO newUser) {

        User user = new User();

        if(newUser.getNickname() == null || newUser.getNickname().isEmpty()) {
            newUser.setError(Constants.EMPTY_FIELD);
        }
        if(newUser.getEmail() == null || newUser.getEmail().isEmpty()) {
            newUser.setError(Constants.EMPTY_FIELD);
        }

        if(newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
            newUser.setError(Constants.EMPTY_FIELD);
        }

        if(!Util.checkEmailFormat(newUser.getEmail())){
            newUser.setError(Constants.EMAIL_NO_VALID);
        }

        BeanUtils.copyProperties(newUser, user);

        String encryptedPassword = passwordEncoder.encode(newUser.getPassword());
        user.setPassword(encryptedPassword);

        User savedUser = userRepository.save(user);


        UserDTO createdUserDTO = new UserDTO();
        BeanUtils.copyProperties(savedUser, createdUserDTO);

        return createdUserDTO;
    }
}
