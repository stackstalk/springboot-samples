package com.stackstalk;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class MovieQueryResolver implements GraphQLQueryResolver {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Optional<Movie> movieById(Integer id) {
		return movieRepository.findById(id);
	}
	
	public List<Movie> movies() {
		return movieRepository.findAll();
	}
}
