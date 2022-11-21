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
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@Value("${claimProcess.URL}")
	private String endpoint;

	private static final String UPDATE_ENDPOINT = "/updateClaimStatus";
	private static final String GET_ENDPOINT = "/getClaimStatus/";

	public ClaimStatus updateClaimStatus(ClaimStatus claimStatus) {
		HttpEntity<ClaimStatus> statusUpdate = new HttpEntity<ClaimStatus>(claimStatus);
		ResponseEntity<ClaimStatus> response = restTemplate.exchange(endpoint + UPDATE_ENDPOINT, HttpMethod.PUT,
				statusUpdate, new ParameterizedTypeReference<ClaimStatus>() {
				});
		return response.getBody();

	}

	public ClaimStatus retrieveClaimStatus(long id) {
		HttpEntity<Long> claimId = new HttpEntity<Long>(id);
		ResponseEntity<ClaimStatus> response = restTemplate.exchange(endpoint + GET_ENDPOINT + id, HttpMethod.GET,
				claimId, new ParameterizedTypeReference<ClaimStatus>() {
				});
		return response.getBody();

	}

}
