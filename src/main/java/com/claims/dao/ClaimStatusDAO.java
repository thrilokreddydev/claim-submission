package com.claims.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claims.model.ClaimStatus;

public interface ClaimStatusDAO extends JpaRepository<ClaimStatus, Integer> {

	ClaimStatus findByClaimId(int claimId);

}
