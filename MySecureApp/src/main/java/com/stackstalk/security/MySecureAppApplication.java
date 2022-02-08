package com.stackstalk.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MySecureAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySecureAppApplication.class, args);
	}

	@GetMapping(path="/sayhello")
	public String sayhello(JwtAuthenticationToken user) {
		System.out.println(user.toString());
        return "Hello there!! ";
	}
	
	@GetMapping(path="/saysecret")
	public String saysecert(JwtAuthenticationToken user) {
		System.out.println(user.toString());
        return "This is a secret!! ";
	}
}
