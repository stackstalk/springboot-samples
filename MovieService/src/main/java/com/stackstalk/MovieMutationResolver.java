package com.stackstalk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class MovieMutationResolver implements GraphQLMutationResolver {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Movie addMovie(Movie input) {
		Movie movie = new Movie(input.getId(), input.getName(), input.getDirector());
		return movieRepository.save(movie);
	}
}
