package com.stackstalk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Component
public class LogInfoHandler {

	@Autowired
	private LogInfoRepository logRepository;
	
	Sinks.Many<LogInfo> logInfoSink = Sinks.many().replay().all();
	
	public Mono<ServerResponse> addLogInfo(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(LogInfo.class)
				.doOnNext(loanInfo -> {
					logInfoSink.tryEmitNext(loanInfo);
				})
				.flatMap(l -> {
					return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(logRepository.save(l), LogInfo.class);
				}).log();
	}
	
	public Mono<ServerResponse> getLoansStream(ServerRequest serverRequest) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_NDJSON)
				.body(logInfoSink.asFlux(), LogInfo.class).log();
	}
}