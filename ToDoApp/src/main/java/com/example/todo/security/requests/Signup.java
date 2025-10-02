package com.example.todo.security.requests;

import com.example.todo.entity.Role;
import com.example.todo.entity.enums.AppRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
public class Signup {
    @NotBlank
    @Size(max = 20)
    private String userName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Setter
    @Getter
    private AppRole role;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password", nullable = false, length = 120)
    private String password;
}
