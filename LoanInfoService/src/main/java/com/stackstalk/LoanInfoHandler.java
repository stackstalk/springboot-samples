package com.stackstalk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Component
public class LoanInfoHandler {

	@Autowired
	private LoanInfoRepository loanRepository;
	
	Sinks.Many<LoanInfo> loanInfoSink = Sinks.many().replay().all();
	
	public Mono<ServerResponse> addLoanInfo(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(LoanInfo.class)
				.doOnNext(loanInfo -> {
					loanInfoSink.tryEmitNext(loanInfo);
				})
				.flatMap(l -> {
					return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(loanRepository.save(l), LoanInfo.class);
				}).log();
	}

	public Mono<ServerResponse> getAllLoans(ServerRequest serverRequest) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(loanRepository.findAll(), 
				LoanInfo.class).log();	
	}

	public Mono<ServerResponse> updateLoanInfo(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(LoanInfo.class)
				.flatMap(l -> {
					return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(loanRepository.save(l), LoanInfo.class);
				}).log();
	}

	public Mono<ServerResponse> deleteLoanInfo(ServerRequest serverRequest) {
		loanRepository.deleteById(serverRequest.pathVariable("loanId"));
		return ServerResponse.ok().build(Mono.empty());
	}
	
	public Mono<ServerResponse> getLoansStream(ServerRequest serverRequest) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_NDJSON)
				.body(loanInfoSink.asFlux(), LoanInfo.class).log();
	}
}