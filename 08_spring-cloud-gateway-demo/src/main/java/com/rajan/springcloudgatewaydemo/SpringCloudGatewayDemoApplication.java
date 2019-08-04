package com.rajan.springcloudgatewaydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringCloudGatewayDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayDemoApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("path_route", r -> r
						.path("/get")
						.filters(f -> f.addRequestHeader("Authorization", "Bearer " + oauthTokenManager().getToken("ResourceId")))
						.uri("http://httpbin.org")
				)
				.build();
	}

	@Bean
	public OauthTokenManager oauthTokenManager(){
		return new OauthTokenManager();
	}
}

class OauthTokenManager{
	Map<String, String> resourceIdVsTokenMap = new HashMap<>();

	public String getToken(String resourceId){
		String token = resourceIdVsTokenMap.get(resourceId);
		if(token == null){
			// Generate new token
			token = "new_token";

		}

		return token;
	}
}
