package com.graymatter.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.graymatter.entities.PendingDiagnosticCenterRequest;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.exceptions.UserOrEmailAlreadyPresent;
import com.graymatter.repositories.PendingDiagnosticCenterRequestRepository;
import com.graymatter.repositories.UserRepository;

@Repository
public class PendingDiagnosticCenterRequestDao {

	@Autowired
	PendingDiagnosticCenterRequestRepository repo;
	
   @Autowired
   UserRepository userRepository;
	
	
	public PendingDiagnosticCenterRequest requestNewDiagnosticCenter(PendingDiagnosticCenterRequest request) throws UserOrEmailAlreadyPresent {
		if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserOrEmailAlreadyPresent("Username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserOrEmailAlreadyPresent("Email is already in use");
        }
        if (repo.existsByUsername(request.getUsername())) {
            throw new UserOrEmailAlreadyPresent("Username is already taken");
        }
        if (repo.existsByEmail(request.getEmail())) {
            throw new UserOrEmailAlreadyPresent("Email is already in use");
        }
        return repo.save(request);
    }

    public PendingDiagnosticCenterRequest approveRequest(int id) throws IdNotFoundException {
        PendingDiagnosticCenterRequest request = repo.findById(id).orElseThrow(() -> new IdNotFoundException("Id "+id+" Not found for the Request"));
        request.setApproved(true);
        repo.save(request);
        return request;
    }

    public List<PendingDiagnosticCenterRequest> getPendingRequests() {
        return repo.findAll().stream().filter(request -> !request.isApproved()).collect(Collectors.toList());
    }
    
    public List<PendingDiagnosticCenterRequest> getAllRequests(){
    	return repo.findAll();
    }
    
    public PendingDiagnosticCenterRequest deleteRequest(int id) throws IdNotFoundException{
    	PendingDiagnosticCenterRequest request = repo.findById(id).orElseThrow(() -> new IdNotFoundException("Id "+id+" Not found for the Request"));
    	repo.deleteById(id);
    	return request;
    }
}
