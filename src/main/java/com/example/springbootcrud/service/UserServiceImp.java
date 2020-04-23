package com.example.springbootcrud.service;

import com.example.springbootcrud.dao.UserDao;
import com.example.springbootcrud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDao;


    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.findByUsername(username);
    }


    public User getUserByid(Long id) {
        return userDao.findById(id);
    }
}
