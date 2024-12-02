package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.UserService;

@Controller

@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/show")
	public String showLoginPage() {
		return "login";
	}

	@PostMapping(value = "/process")
	public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
	    // Call the validateUser method in the service to validate the username and password
	    if (userService.validateUser(username, password) == true) {
	        // If validation is successful, redirect to the "welcome" page
	        return "welcome"; 
	    } else {
	        // If validation fails, add an error message to the model and return to the login page
	        model.addAttribute("error", "Invalid username or password"); // Add error message to the model
	        return "login"; // Return to the login page with the error message
	    }
	}

}
