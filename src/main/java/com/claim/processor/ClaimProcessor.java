package com.claim.processor;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.claim.dao.ClaimsDAO;
import com.claim.entity.Claim;
import com.claim.exception.NoResultsException;
import com.claim.exception.ValidationException;

@Component
public class ClaimProcessor {

	@Autowired
	private ClaimsDAO claimsDAO;

	public Claim saveClaim(Claim claim) throws ValidationException {

		Claim existingClaim = claimsDAO.findByVehicleVin(claim.getVehicleVin());
		if (existingClaim != null) {
			throw new ValidationException("Claim already exist with Id: " + existingClaim.getClaimId());
		}
		claim.setClaimId(Instant.now().getEpochSecond());
		return claimsDAO.save(claim);
	}

	public Claim getClaim(long id) throws NoResultsException {

		Claim claim = claimsDAO.findByClaimId(id);
		if (claim == null) {
			throw new NoResultsException("No claim exist with Id: " + id);
		}
		return claim;
	}

	public List<Claim> getAllClaims() {

		return claimsDAO.findAll();
	}

}
