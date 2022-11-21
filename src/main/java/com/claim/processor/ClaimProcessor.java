package com.claim.processor;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.claim.dao.ClaimsDAO;
import com.claim.delegator.ClaimStatusDelegator;
import com.claim.entity.Claim;
import com.claim.entity.ClaimStatus;
import com.claim.exception.NoResultsException;
import com.claim.exception.ValidationException;

@Component
public class ClaimProcessor {

	@Autowired
	private ClaimsDAO claimsDAO;

	@Autowired
	private ClaimStatusDelegator statusDelegator;

	public Claim saveClaim(Claim claim) throws ValidationException {

		Claim existingClaim = claimsDAO.findByVehicleVin(claim.getVehicleVin());
		if (existingClaim != null) {
			throw new ValidationException("Claim already exist with Id: " + existingClaim.getClaimId());
		}
		claim.setClaimId(Instant.now().getEpochSecond());
		claim.setClaimType("Auto");
		ClaimStatus status = new ClaimStatus();
		status.setClaimId(claim.getClaimId());
		statusDelegator.updateClaimStatus(status);
		return claimsDAO.save(claim);
	}

	public Claim getClaim(long id) throws NoResultsException {
		Claim claim = claimsDAO.findByClaimId(id);
		if (claim == null) {
			throw new NoResultsException("No claim exist with Id: " + id);
		}
		claim.setClaimStatus(statusDelegator.retrieveClaimStatus(id).getClaimStatus());
		return claim;
	}

	public List<Claim> getAllClaims() {
		List<Claim> claimsList = claimsDAO.findAll();
		for (Claim existingClaim : claimsList) {
			existingClaim
					.setClaimStatus(statusDelegator.retrieveClaimStatus(existingClaim.getClaimId()).getClaimStatus());
		}
		return claimsList;
	}

	public ClaimStatus updateClaimStatus(ClaimStatus claimStatus) throws ValidationException {
		Claim claim = claimsDAO.findByClaimId(claimStatus.getClaimId());
		if (claim == null) {
			throw new ValidationException("No claim exist with Id: " + claimStatus.getClaimId());
		}
		return statusDelegator.updateClaimStatus(claimStatus);
	}
}
