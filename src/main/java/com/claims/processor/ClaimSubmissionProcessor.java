package com.claims.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.claims.dao.ClaimsDAO;
import com.claims.model.Claims;

@Component
public class ClaimSubmissionProcessor {
	
	@Autowired
	private ClaimsDAO claimsDAO;
	
	public Claims checkClaim(String vehicleVin){ 
		Claims data = claimsDAO.findByVehicleVin(vehicleVin);
		return data;
	}

}
