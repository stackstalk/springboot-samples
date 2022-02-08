package com.stackstalk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class LogInfoRouter {
	
	@Bean
	public RouterFunction<ServerResponse> routes(LogInfoHandler handler) {
		return RouterFunctions.route(RequestPredicates.POST("/logs"), handler::addLogInfo)
				.andRoute(RequestPredicates.GET("/stream"), handler::getLoansStream);
	}
}
