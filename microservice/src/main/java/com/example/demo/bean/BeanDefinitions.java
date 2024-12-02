package com.example.demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = "com.example.demo")
@Configuration
public class BeanDefinitions {

	@Bean
	public RestTemplate restTemplate(){
System.out.println("Bean of rest Template Created");
		return new RestTemplate();
	}
	
}
