package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;

public interface UserService {

	String register(UserDto user);
    boolean validateUser(String username, String password);
    List<UserDto> fetchAllUsers();

}
