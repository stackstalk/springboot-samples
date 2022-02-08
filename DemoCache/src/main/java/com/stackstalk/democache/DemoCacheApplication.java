package com.stackstalk.democache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCacheApplication.class, args);
	}

}
