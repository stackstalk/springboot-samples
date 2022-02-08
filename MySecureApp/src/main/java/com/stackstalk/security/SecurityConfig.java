package com.stackstalk.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/").permitAll()
        .anyRequest().authenticated()
        .accessDecisionManager(accessDecisionManager())
        .and()
        .oauth2ResourceServer().jwt();
    }

	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = Arrays.asList(
				new OPABasedVoter("http://localhost:8181/v1/data/demoapi/authz/allow"));
		return new UnanimousBased(decisionVoters);
	}
}


/*http.authorizeRequests()
.antMatchers("/").permitAll()
.anyRequest().authenticated()
.and()
.httpBasic();*/

/*
@Override  
protected void configure(AuthenticationManagerBuilder auth) throws Exception {  
    auth.inMemoryAuthentication()  
        .withUser("user")  
        .password("{noop}pass") // Spring Security 5 requires specifying the password storage format  
        .roles("USER");  
}*/