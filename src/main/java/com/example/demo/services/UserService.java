package com.example.demo.services;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    User getUserByLogin(String login);
    User getUserByEmail(String email);
    User getUserByPhoneNumber(String phoneNumber);
    String deleteUser(Long id);
    User editUser(User user, Long id);
}
