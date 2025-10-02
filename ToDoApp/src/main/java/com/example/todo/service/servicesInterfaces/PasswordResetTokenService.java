package com.example.todo.service.servicesInterfaces;

import com.example.todo.entity.PasswordResetToken;
import com.example.todo.entity.User;

public interface PasswordResetTokenService {
    public PasswordResetToken createToken(User user);
    public boolean validateToken(String token);
    public void resetPassword(String token, String newPassword);
}
