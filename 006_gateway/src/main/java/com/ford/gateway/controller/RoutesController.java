package com.ford.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoutesController {

	@RequestMapping(value="/available")
	public @ResponseBody String available(){
		return "Spring in Action";
	}
}
