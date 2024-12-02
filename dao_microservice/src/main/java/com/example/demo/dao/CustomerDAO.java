package com.example.demo.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.User;


public interface CustomerDAO {
    void saveUser(User user);
    User findByUsername(String username);
    List<User> findAll();
}
