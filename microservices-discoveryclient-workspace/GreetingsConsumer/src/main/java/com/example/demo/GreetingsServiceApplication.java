package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class })
@EnableEurekaClient
@EnableFeignClients
public class GreetingsServiceApplication implements CommandLineRunner{
	
	@Autowired
    private DiscoveryClient discoveryClient;

	public static void main(String[] args) {
		SpringApplication.run(GreetingsServiceApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Command line runner run method --------");
		discoveryClient.getInstances("greetings-service")
							.forEach(System.out::println);
	}
	
}
