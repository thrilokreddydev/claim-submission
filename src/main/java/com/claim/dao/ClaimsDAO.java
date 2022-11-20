package com.claim.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.entity.Claim;

public interface ClaimsDAO extends JpaRepository<Claim, Long> {

	Claim findByVehicleVin(String vehicleVin);
	
	Claim findByClaimId(long id);

}
