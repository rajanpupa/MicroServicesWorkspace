package com.rajan.redisrabbitdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.rajan.redisrabbitdemo.repository.MessageDatabase;

@Component
public class RedisMessageService {
	
	@Autowired
	MessageDatabase messageDatabase;
	
	@Cacheable(value = "message", key = "#key", cacheManager = "redisCacheManager", unless = "#result != null")
	public String getMessage(String key) {
		return messageDatabase.find(key);
	}
	
	@CachePut(value = "#value", key = "#key")
	public void update(String key, String value) {
		messageDatabase.save(key, value);
	}

}
