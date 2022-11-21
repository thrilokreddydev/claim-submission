package com.claim.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClaimStatus {
	
	@Id
	@Column(unique = true, nullable=false)
	private long claimId;
	private String claimStatus;
	
	public long getClaimId() {
		return claimId;
	}
	public void setClaimId(long claimId) {
		this.claimId = claimId;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

}
