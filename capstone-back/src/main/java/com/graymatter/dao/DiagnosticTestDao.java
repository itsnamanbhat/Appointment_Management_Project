
package com.graymatter.dao;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.graymatter.entities.DiagnosticTest;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.repositories.DiagnosticCenterRepository;
import com.graymatter.repositories.DiagnosticTestRepository;

import jakarta.transaction.Transactional;

@Repository
public class DiagnosticTestDao {

	@Autowired
	DiagnosticTestRepository repo;
	
	@Autowired
	DiagnosticCenterRepository diagnosticCenterRepository;

	public List<DiagnosticTest> getAllDiagnosticTest(){
		return repo.findAll();
	}
	public DiagnosticTest addNewDiagnosticTest(DiagnosticTest diagnosticTest) {
		return repo.save(diagnosticTest);
	}
	public DiagnosticTest updateDiagnosticTestDetails(int id,DiagnosticTest updatedDiagnosticTest) throws IdNotFoundException {
		DiagnosticTest existingDiagnosticTest=repo.findById(id).orElseThrow(()->new IdNotFoundException("Diagnostic Test id: "+id+" is not present"));
		if(updatedDiagnosticTest.getAppointments()!=null)
			existingDiagnosticTest.setAppointments(updatedDiagnosticTest.getAppointments());
		if(updatedDiagnosticTest.getDiagnosticCenter()!=null)
			existingDiagnosticTest.setDiagnosticCenter(updatedDiagnosticTest.getDiagnosticCenter());
		if(updatedDiagnosticTest.getTestName()!=null)
			existingDiagnosticTest.setTestName(updatedDiagnosticTest.getTestName());
		if(updatedDiagnosticTest.getTestPrice()!=0)
			existingDiagnosticTest.setTestPrice(updatedDiagnosticTest.getTestPrice());
		return repo.save(existingDiagnosticTest);
		
		
	}
	public DiagnosticTest getDiagnosticTestById(int id) throws IdNotFoundException {
		return repo.findById(id).orElseThrow(()->new IdNotFoundException("Diagnostic Test id: "+id+" is not present"));
	}
	
	@Transactional
	public DiagnosticTest deleteDiagnosticTestById(int id) throws IdNotFoundException {
		DiagnosticTest test=repo.findById(id).orElseThrow(()->new IdNotFoundException("Diagnostic Test id: "+id+" is not present"));
		repo.deleteById(id);
		return test;
	}

	public double getPriceOfTest(String testName)
	{
		return repo.findByTestName(testName).get(0).getTestPrice();
	}

	
	public DiagnosticTest addTest(int diagnosticCentreId, int testId) {
          return null;
      
    }
	public List<DiagnosticTest> getTestsOfCenter(int id) {
		// TODO Auto-generated method stub
		return repo.findByCenter(id);
	}


}
	

