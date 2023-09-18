package com.example.demo.service;

import com.example.demo.models.User;

public interface UserService {
    User createUser(User user);
    User updateUser(User user);
    User findUserById(Long id);
    User findUserByEmail(String email);
    User findUserByEmailAndPassword(String email, String password);
}
