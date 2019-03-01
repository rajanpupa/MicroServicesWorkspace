package com.rajan.redisrabbitdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rajan.redisrabbitdemo.service.RabbitServiceCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    RabbitServiceCloud rabbitServiceCloud;

    @GetMapping("/test")
    public String test(@RequestParam("message")String message) throws JsonProcessingException {
        rabbitServiceCloud.publishToMessageQueue(message);
        return "Hello World";
    }
}
