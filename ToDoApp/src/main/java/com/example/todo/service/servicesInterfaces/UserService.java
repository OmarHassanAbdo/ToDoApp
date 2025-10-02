package com.example.todo.service.servicesInterfaces;

import com.example.todo.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User createUser(User user);

    public User updateUser(User user);

    public User deleteUser(Long id);
}
