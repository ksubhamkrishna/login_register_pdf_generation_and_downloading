package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.model.User;


@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CustomerDAO customerDAOImpl;

    @PostMapping(value = "/register")
    public ResponseEntity<String> showLoginPage(@RequestBody User user) {
       customerDAOImpl.saveUser(user);  // Return the login page view
       
       return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    @PostMapping(value = "/process")
    public ResponseEntity<Boolean> loginUser(@RequestBody User user) {
        try {
            User users = customerDAOImpl.findByUsername(user.getUsername());
            if (users != null && users.getPassword().equals(user.getPassword())) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
   

}
