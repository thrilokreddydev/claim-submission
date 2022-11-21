package com.claim.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {
	
	@Value("${claimProcess.userId}")
	private String userId;
	
	@Value("${claimProcess.password}")
	private String password;
	
	@Bean(name="restTemplate")
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.basicAuthentication(userId, password).build();
	}

}
