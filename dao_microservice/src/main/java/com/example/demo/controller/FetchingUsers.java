package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.model.User;

@RestController
@RequestMapping("/fetch")
public class FetchingUsers {
	
	@Autowired
	private CustomerDAO customerDAOImpl; 
	
	 @GetMapping("/download")
	    public List<User> getAllUsers() {
	        List<User> all_users = customerDAOImpl.findAll();
	        return all_users;
	    
	    }
            
}
