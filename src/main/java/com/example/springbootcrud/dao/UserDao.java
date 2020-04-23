package com.example.springbootcrud.dao;

import com.example.springbootcrud.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    
    List<User> listUsers();
    
    void delete(Long id);
    
    void update(User user);
    
    User findById(Long id);

    User findByUsername(String username);
}
