package com.example.recipees.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Recipees {
    @Id
    private String id;
    private String nombre;
    private String steps;
    private String createdBy;
}
