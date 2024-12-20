package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.bean.BeanDefinitions;
import com.example.demo.dto.UserDto;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

@Service
public class UserServiceImpl implements UserService {

	RestTemplate restTemplate = new RestTemplate();

//    @Override
//    public boolean validateUser(String username, String password) {
//        User user = customerDAO.findByUsername(username);
//        return user != null && user.getPassword().equals(password);
//    }

	@Override
	public String register(UserDto user) {
		String url = "http://localhost:9021/POC/login/register";
		System.out.println("POST url is: " + url);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<UserDto> postData = new HttpEntity<>(user, headers);

		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, postData, String.class);
			HttpStatus statusCode = responseEntity.getStatusCode();
			String responseBody = responseEntity.getBody();
			System.out.println("Response Status Code: " + statusCode);
			System.out.println("Response Body: " + responseBody);
			if (statusCode.is2xxSuccessful()) {
				return "Registration successful!";
			} else {
				return "Registration failed: " + responseBody;
			}
		} catch (HttpClientErrorException | HttpServerErrorException ex) {
			System.out.println("Error during registration: " + ex.getMessage());
			return "Error during registration: " + ex.getStatusCode();
		} catch (Exception ex) {
			System.out.println("Unexpected error: " + ex.getMessage());
			return "Unexpected error: " + ex.getMessage();
		}
	}

	public boolean validateUser(String username, String password) {
		String url = "http://localhost:9021/POC/login/process";
		System.out.println("POST url is: " + url);

		// Prepare the headers and the request body (DTO or map)
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Assuming you have a simple UserDto (username, password) class
		UserDto userDto = new UserDto(username, password);
		HttpEntity<UserDto> postData = new HttpEntity<>(userDto, headers);

		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, postData, String.class);
			String responseBody = response.getBody();
			Boolean responseBodyBoolean = Boolean.parseBoolean(responseBody);

			if (responseBodyBoolean) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public List<UserDto> fetchAllUsers() {
    	
    	String BASE_URL = "http://localhost:9021/POC/fetch/download";
    	
        ResponseEntity<UserDto[]> response = restTemplate.getForEntity(BASE_URL, UserDto[].class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Arrays.asList(response.getBody()); // Convert array to list
        }
        return List.of(); // Return an empty list if no users are found
        
      }
}
