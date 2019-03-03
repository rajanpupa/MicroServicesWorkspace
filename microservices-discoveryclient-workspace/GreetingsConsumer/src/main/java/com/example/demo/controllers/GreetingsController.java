package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingsController {
	
	@Autowired
	//@LoadBalanced
	RestTemplate restTemplate;

	@RequestMapping("/")
	public String test() {
		return "Greetings from greeting consumer";
	}
	
	@RequestMapping("/consume")
	public String consume() {
		String response = "Greetings from greeting consumer";
		
		ParameterizedTypeReference<String> reference = new ParameterizedTypeReference<String>() { };

		
		ResponseEntity<String> r = this.restTemplate.exchange("http://greetings-service/", HttpMethod.GET, null, reference);

		System.out.println("--- response " + r.getBody());
		
		return response;
	}
}
