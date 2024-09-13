package com.graymatter.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.graymatter.entities.Appointment;
import com.graymatter.entities.TestResult;

public interface TestResultRepository extends JpaRepository<TestResult, Integer>{
	
	
	List<TestResult> findByAppointmentIdIn(List<Integer> appointmentIds);
	
	 @Query("SELECT t FROM TestResult t WHERE t.appointment.patient.user.username = :username")
	    List<TestResult> findAllTestResultsByPatientUsername(@Param("username") String username);

	     void deleteByAppointmentId(int id);

//	    @Query("SELECT t FROM TestResult t WHERE t.id = :testResultId")
//	    TestResult findTestResultById(@Param("testResultId") int testResultId);
	
}
