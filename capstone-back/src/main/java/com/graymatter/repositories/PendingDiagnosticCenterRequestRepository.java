package com.graymatter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graymatter.entities.PendingDiagnosticCenterRequest;

public interface PendingDiagnosticCenterRequestRepository extends JpaRepository<PendingDiagnosticCenterRequest, Integer>{

	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
