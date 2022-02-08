package com.stackstalk.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class OPABasedVoter implements AccessDecisionVoter<Object> {

	private String opaAuthUrl;
	
	OPABasedVoter(String opaAuthUrl) {
		this.opaAuthUrl = opaAuthUrl;
	}
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection attributes) {
		
		if (!(object instanceof FilterInvocation)) {
			return ACCESS_ABSTAIN;
		}
		
		FilterInvocation filter = (FilterInvocation) object;
		
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("method", filter.getRequest().getMethod());
		input.put("api", filter.getRequest().getRequestURI());
		input.put("jwt", authentication.getPrincipal());
		
		WebClient webClient = WebClient.create();
		OPAResponse response = webClient.post()
				.uri(this.opaAuthUrl)
				.body(Mono.just(new OPARequest(input)), OPARequest.class)
				.retrieve()
				.bodyToMono(OPAResponse.class)
				.block();

        if (response == null || response.getResult() == false) {
            return ACCESS_DENIED;
        }		
		
		return ACCESS_GRANTED;
	}

}
