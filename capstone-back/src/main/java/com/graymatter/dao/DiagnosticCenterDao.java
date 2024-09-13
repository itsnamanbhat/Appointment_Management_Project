package com.graymatter.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.graymatter.entities.DiagnosticCenter;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.repositories.DiagnosticCenterRepository;

@Repository
public class DiagnosticCenterDao {

	@Autowired
	DiagnosticCenterRepository repo;

	public List<DiagnosticCenter> getAllDiagnosticCenters() {

		return repo.findAll();
	}

	public DiagnosticCenter addDiagnosticCenter(DiagnosticCenter diagnosticCenter) {
		return repo.save(diagnosticCenter);
	}

	public DiagnosticCenter getDiagnosticCenterById(int diagnosticCenterId) throws IdNotFoundException {

		return repo.findById(diagnosticCenterId).orElseThrow(()->new IdNotFoundException("DiagnosticCenter id: "+diagnosticCenterId+" is not present"));
	}

	public DiagnosticCenter updateDiagnosticCenter(int id, DiagnosticCenter diagnosticCenter) throws IdNotFoundException {

		 DiagnosticCenter existingCenter = repo.findById(id).orElseThrow(()->new IdNotFoundException("DiagnosticCenter id: "+id+" is not present"));
	   

	        // Update fields if they are not null
	        if (diagnosticCenter.getName() != null && !diagnosticCenter.getName().isEmpty()) {
	            existingCenter.setName(diagnosticCenter.getName());
	        }
	        if (diagnosticCenter.getContactNO() != null && !diagnosticCenter.getContactNO().isEmpty()) {
	            existingCenter.setContactNO(diagnosticCenter.getContactNO());
	        }
	        if (diagnosticCenter.getAddress() != null && !diagnosticCenter.getAddress().isEmpty()) {
	            existingCenter.setAddress(diagnosticCenter.getAddress());
	        }
	        if (diagnosticCenter.getDiagnosticTests() != null) {
	            existingCenter.setDiagnosticTests(diagnosticCenter.getDiagnosticTests());
	        }
	        if (diagnosticCenter.getAppointments() != null) {
	            existingCenter.setAppointments(diagnosticCenter.getAppointments());
	        }


	        // Save the updated DiagnosticCenter
	        DiagnosticCenter savedCenter = repo.save(existingCenter);

	        System.out.println(savedCenter);
	        return savedCenter;	
	}
	public String  deleteDiagnosticCenter(int id) throws IdNotFoundException {
		Optional<DiagnosticCenter> d= repo.findById(id);
		if(d.isPresent()) {
			repo.delete(d.get());
		}
		else {
			throw new IdNotFoundException("id not found");
		}
		return "successfully deleted";
	}
	
	public List<DiagnosticCenter> findByDiagnosticTests(List<String> diagnosticTestIds){
		return repo.findByDiagnosticTestNames(diagnosticTestIds);
	}

	public DiagnosticCenter getDiagnosticCenterByUserId(int id) {
		// TODO Auto-generated method stub
		Optional<DiagnosticCenter>d =repo.findByUserId(id);
		if(d.get().getAddress().isEmpty()) {
			
			return null;
		}
		return d.get();
		
	}
	
	
}
