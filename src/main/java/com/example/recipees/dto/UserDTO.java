package com.example.recipees.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String nickname;
    private String email;
    private String password;
    private String error;
}
