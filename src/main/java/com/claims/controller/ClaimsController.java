package com.claims.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.claims.dao.ClaimsDAO;
import com.claims.model.Claims;
import com.claims.processor.ClaimProcessor;

@RestController
public class ClaimsController {
	
	@Autowired
	private ClaimsDAO claimsDAO;
	
	@Autowired
	private ClaimProcessor claimProcessor;
	
	@PostMapping(path="/submitClaim")
	public String submitClaim(@RequestBody Claims claim) {
		
		Random rand = new Random();
		int generatedId = 100000 + rand.nextInt(900000);
		claim.setClaimId(generatedId);
		Claims verifyClaim = claimProcessor.checkClaim(claim.getVehicleVin());
		if(!StringUtils.isEmpty(verifyClaim)) {
			return "Claim Already Exist";
		}
		claimsDAO.save(claim);
		return "Claim Submitted";		
	}
	
	@GetMapping("/getClaimDetails/{vehicleVin}")
	public Claims getClaimStatus(@PathVariable String vehicleVin){ 
		Claims data = claimsDAO.findByVehicleVin(vehicleVin);
		return data;
	}
	 

}
