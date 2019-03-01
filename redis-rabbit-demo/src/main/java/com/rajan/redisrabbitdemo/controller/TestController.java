package com.rajan.redisrabbitdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rajan.redisrabbitdemo.service.RabbitServiceCloud;
import com.rajan.redisrabbitdemo.service.RedisMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    RabbitServiceCloud rabbitServiceCloud;
    
    @Autowired
    RedisMessageService redisMessageService;

    @GetMapping("/test")
    public String test(@RequestParam("message")String message) throws JsonProcessingException {
        rabbitServiceCloud.publishToMessageQueue(message);
        return "Hello World";
    }
    
    @PostMapping("/redis-test/{key}")
    public String setToRedis(@PathVariable("key")String key, @RequestParam("message")String message) {
    	redisMessageService.update(key, message);
    	return "working on it";
    }
    
    @GetMapping("/redis-test/{key}")
    public String getFromRedis(@PathVariable("key")String key) {
    	return redisMessageService.getMessage(key);
    }
}
