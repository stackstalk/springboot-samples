package com.stackstalk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class LoanInfoRouter {
	
	@Bean
	public RouterFunction<ServerResponse> routes(LoanInfoHandler handler) {
		return RouterFunctions.route(RequestPredicates.GET("/loans"), handler::getAllLoans)
				.andRoute(RequestPredicates.POST("/loans"), handler::addLoanInfo)
				.andRoute(RequestPredicates.PUT("/loans"), handler::updateLoanInfo)
				.andRoute(RequestPredicates.DELETE("/loans/{loanId}"), handler::deleteLoanInfo)
				.andRoute(RequestPredicates.GET("/stream"), handler::getLoansStream);
	}
}
