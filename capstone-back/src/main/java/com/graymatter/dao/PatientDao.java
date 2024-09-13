package com.graymatter.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.graymatter.entities.Appointment;
import com.graymatter.entities.Patient;
import com.graymatter.entities.TestResult;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.repositories.PatientRepository;
import com.graymatter.repositories.TestResultRepository;
@Repository
public class PatientDao {

	@Autowired
	PatientRepository patientRepo;
	
	@Autowired
	TestResultRepository testResultRepo;
	
	@Autowired
	AppointmentDao dao;

	public List<TestResult> getAllTestResults() {
		return testResultRepo.findAll();
		}

	public List<Patient> getPatientByUsername(String patientUsername) {
		
		return patientRepo.findByUsername(patientUsername);
	}

	public boolean isPresent(int id) {
		Patient p= patientRepo.findById(id).get();
		return p!=null;
	}

	public Patient addPatient(Patient patient) {
		return patientRepo.save(patient);
		
	}

	public List<TestResult> getTestResultByUserName(String patientUserName) {
		return testResultRepo.findAllTestResultsByPatientUsername(patientUserName);
	}

	public List<Patient> getAllPatients() {
		return patientRepo.findAll();
		}

	public Patient getPatientById(int id) throws IdNotFoundException {
		return patientRepo.findById(id).orElseThrow(()->new IdNotFoundException("patient id: "+id+" is not present"));
	}

	public Patient deletePatientById(int id) throws IdNotFoundException {
		Patient p= patientRepo.findById(id).orElseThrow(()->new IdNotFoundException("patient id: "+id+" is not present"));
	    List<Appointment> appointments = p.getAppointments();

	        // Delete each appointment by ID
	        for (Appointment appointment : appointments) {
	           dao.deleteAppointmentById(appointment.getId());
	        }

		patientRepo.deleteById(id);
		return p;
	}
	public List<Patient> getPatientByMobile(String mobileNo){
	return patientRepo.findByPhoneNo(mobileNo);
	
		
	}
	public Patient updatePatient(int id, Patient updatedPatient) throws IdNotFoundException {
		Patient existingPatient= patientRepo.findById(id).orElseThrow(()->new IdNotFoundException("The patient with the id : "+id+" not found"));
		 
	            if (updatedPatient.getName() != null) {
	                existingPatient.setName(updatedPatient.getName());
	            }
	            if (updatedPatient.getPhoneNo() != null) {
	                existingPatient.setPhoneNo(updatedPatient.getPhoneNo());
	            }
	            if (updatedPatient.getAge() != 0) {
	                existingPatient.setAge(updatedPatient.getAge());
	            }
	            if (updatedPatient.getGender() != null) {
	                existingPatient.setGender(updatedPatient.getGender());
	            }
	            if (updatedPatient.getAadharNumber() != null) {
	                existingPatient.setAadharNumber(updatedPatient.getAadharNumber());
	            }
	            if (updatedPatient.getUser() != null) {
	                existingPatient.setUser(updatedPatient.getUser());
	            }

	            return patientRepo.save(existingPatient);
	}

}
