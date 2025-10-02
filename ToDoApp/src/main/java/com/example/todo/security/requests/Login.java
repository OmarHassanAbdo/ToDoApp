package com.example.todo.security.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
    private String username;

    private String password;
}
