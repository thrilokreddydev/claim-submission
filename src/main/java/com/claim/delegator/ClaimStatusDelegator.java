package com.claim.delegator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClaimStatusDelegator {
	
	@Autowired
	@Qualifier("resTemplate")
	private RestTemplate restTemplate;
	
	@Value("${claimProcess.URL}")
	private String endpoint;
	
	

}
