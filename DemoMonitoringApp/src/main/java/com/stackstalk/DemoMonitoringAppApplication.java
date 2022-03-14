package com.stackstalk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;

@SpringBootApplication
@RestController
@Timed
public class DemoMonitoringAppApplication {

	@Autowired
	ItemService itemService;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoMonitoringAppApplication.class, args);
	}

	@PostMapping("/books")
	@Timed("books.api")
	public String orderBook() {
		return itemService.orderBook();
	}
	
	@PostMapping("/movies")
	@Timed("movies.api")
	public String orderMovie() {
		return itemService.orderMovie();
	}
}

