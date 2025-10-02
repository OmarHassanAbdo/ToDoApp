package com.example.todo.service;

import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.servicesInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User is not found")
        );

    }

    @Override
    public User createUser(User user) {
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("User not found with id: " + user.getId());
        }
        return userRepository.save(user);
    }


    @Override
    public User deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.deleteById(id);
        return user;
    }
}
