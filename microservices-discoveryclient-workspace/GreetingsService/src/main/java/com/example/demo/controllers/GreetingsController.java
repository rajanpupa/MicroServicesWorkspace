package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

	@RequestMapping("/")
	public String test() {
		return "Greetings from greeting server";
	}
}
