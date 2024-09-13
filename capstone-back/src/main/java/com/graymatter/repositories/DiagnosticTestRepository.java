package com.graymatter.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.graymatter.entities.DiagnosticTest;
import java.util.Set;
import com.graymatter.entities.DiagnosticCenter;




public interface DiagnosticTestRepository extends JpaRepository<DiagnosticTest, Integer>{

	List<DiagnosticTest> findByTestName(String testName);
	
	 @Query(value="select * from diagnostic_test where diagnostic_center_id=:id",nativeQuery = true)
	 List<DiagnosticTest> findByCenter(@Param("id") int id);
	

}
