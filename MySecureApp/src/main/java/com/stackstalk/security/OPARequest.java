package com.stackstalk.security;

import java.util.Map;

public class OPARequest {

    Map<String, Object> input;

    public OPARequest(Map<String, Object> input) {
        this.input = input;
    }

	public Map<String, Object> getInput() {
		return input;
	}
}