package com.example.todo.dto;

import lombok.Data;

@Data
public class LoginResponse {
    boolean status;
    String token;
    String tokenType;
    long expiresAt;
}
