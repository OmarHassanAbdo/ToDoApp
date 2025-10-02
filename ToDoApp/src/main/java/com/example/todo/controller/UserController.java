package com.example.todo.controller;

import com.example.todo.dto.ApiResponse;
import com.example.todo.entity.User;
import com.example.todo.service.servicesInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/userData")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(true, "Users retrieved successfully", users));
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "User found", user));
    }

    @PostMapping("/createUser")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        System.out.println("user data:" + user.getRole());
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(new ApiResponse<>(true, "User created successfully", createdUser));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
            user.setId(id);
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(new ApiResponse<>(true, "User updated successfully", updatedUser));

    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable Long id) {
            User deletedUser = userService.deleteUser(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully", deletedUser));

    }
}
