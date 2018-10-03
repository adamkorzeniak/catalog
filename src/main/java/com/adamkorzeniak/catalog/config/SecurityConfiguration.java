package com.adamkorzeniak.catalog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    private final String[] unauthorizedResources = new String[]{
    };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.httpBasic()
			.and()
				.authorizeRequests()
				.antMatchers(unauthorizedResources)
				.permitAll()
			.anyRequest()
				.authenticated()
			.and()
				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
		http.csrf().disable();
	}
	
}