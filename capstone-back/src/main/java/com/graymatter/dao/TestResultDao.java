package com.graymatter.dao;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.graymatter.entities.Appointment;
import com.graymatter.entities.TestResult;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.repositories.AppointmentRepository;
import com.graymatter.repositories.TestResultRepository;

@Repository
public class TestResultDao {

	@Autowired
	TestResultRepository repo;
	
	@Autowired
	AppointmentRepository appointmentRepo;
	
	public List<TestResult> getAllTestResults(){
		return repo.findAll();
	}
	public TestResult addTestResult(TestResult testResult) {
		return repo.save(testResult);
	}
	public TestResult updateTestResult(int id,TestResult updatedTestResult) throws IdNotFoundException {
		TestResult existingTestResult=repo.findById(id).orElseThrow(()->new IdNotFoundException("Diagnostic Test id: "+id+" is not present"));
		if (updatedTestResult.getTestName()!=null) {
	        existingTestResult.setTestName(updatedTestResult.getTestName());
	    }
	    if (updatedTestResult.getAppointment() != null) {
	        existingTestResult.setAppointment(updatedTestResult.getAppointment());
	    }
		return repo.save(existingTestResult);
	}
	public TestResult getTestResultById(int id) throws IdNotFoundException {
		return repo.findById(id).orElseThrow(()->new IdNotFoundException("Diagnostic Test id: "+id+" is not present"));
	}
	public TestResult deleteTestResultById(int id) throws IdNotFoundException {
		TestResult existingTestResult=repo.findById(id).orElseThrow(()->new IdNotFoundException("Diagnostic Test id: "+id+" is not present"));
        repo.deleteById(id);
        return existingTestResult;
	}
	public List<TestResult> viewTestResultByPatient(int patient_id){
		List<Appointment> appointments=appointmentRepo.findByPatient(patient_id);
		List<Integer> appointmentIds=appointments.stream().map(Appointment::getId).collect(Collectors.toList());
		return repo.findByAppointmentIdIn(appointmentIds);
	}
	
}
