package com.claim.delegator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.claim.entity.ClaimStatus;

@Component
public class ClaimStatusDelegator {
	
	@Autowired
	@Qualifier("resTemplate")
	private RestTemplate restTemplate;
	
	@Value("${claimProcess.URL}")
	private String endpoint;
	
	private String updateStatus = "/updateClaimStatus";
	private String getStatus ="/getClaimStatus/";
	
	public ClaimStatus updateClaimStatus (ClaimStatus claimStatus){
		
		HttpEntity<ClaimStatus> statusUpdate = new HttpEntity<ClaimStatus>(claimStatus);
		ResponseEntity<ClaimStatus> response = restTemplate.exchange(endpoint+updateStatus, HttpMethod.PUT,statusUpdate,new ParameterizedTypeReference<ClaimStatus>() {
		});
		return response.getBody();
		
	}
	
	public ClaimStatus retrieveClaimStatus (long id){
		
		endpoint=endpoint+getStatus+id;
		HttpEntity<Long> claimId = new HttpEntity<Long>(id);
		ResponseEntity<ClaimStatus> response = restTemplate.exchange(endpoint, HttpMethod.GET,claimId,new ParameterizedTypeReference<ClaimStatus>() {
		});
		return response.getBody();
		
	}

}
