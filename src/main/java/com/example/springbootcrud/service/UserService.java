package com.example.springbootcrud.service;

import com.example.springbootcrud.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    void delete(Long id);

    void update(User user);

    User getUserByName(String username);

    User getUserByid(Long id);
}
