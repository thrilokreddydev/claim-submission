package com.claim.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.entity.Claim;
import com.claim.exception.NoResultsException;
import com.claim.exception.ValidationException;
import com.claim.processor.ClaimProcessor;

@RestController
@RequestMapping(value = "/claim")
@CrossOrigin
public class ClaimController {

	@Autowired
	private ClaimProcessor claimProcessor;

	@PostMapping()
	public ResponseEntity<?> saveClaim(@RequestBody Claim claim) {
		try {
			return ResponseEntity.ok().body(claimProcessor.saveClaim(claim));
		} catch (ValidationException ve) {
			return ResponseEntity.badRequest().body(ve.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error occured while saving claim | " + e.getMessage());
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getClaim(@PathVariable Long id) {
		try {
			return ResponseEntity.ok().body(claimProcessor.getClaim(id));
		} catch (NoResultsException nre) {
			return ResponseEntity.badRequest().body(nre.getMessage());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error occured while retrieving claim | " + e.getMessage());
		}
	}

	@GetMapping()
	public ResponseEntity<?> getAllClaims() {
		try {
			return ResponseEntity.ok().body(claimProcessor.getAllClaims());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error occured while retrieving claims | " + e.getMessage());
		}
	}

}
