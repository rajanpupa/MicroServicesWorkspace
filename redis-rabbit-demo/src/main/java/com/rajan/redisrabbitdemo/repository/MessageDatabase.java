package com.rajan.redisrabbitdemo.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class MessageDatabase {

	Map<String, String> database = new HashMap<>();
	
	
	public void save(String key, String value) {
		System.out.println("Updating in the database: " + key);
		database.put(key, value);
	}
	
	public String find(String key) {
		System.out.println("Searching in the database: " + key);
		return database.get(key);
	}
	
}
