package com.graymatter.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.graymatter.entities.DiagnosticCenter;
import com.graymatter.entities.DiagnosticTest;

public interface DiagnosticCenterRepository extends JpaRepository<DiagnosticCenter, Integer>{
	List<DiagnosticCenter> findByDiagnosticTests(List<DiagnosticTest> diagnosticTests);
	
	@Query("SELECT dc FROM DiagnosticCenter dc JOIN dc.diagnosticTests dt " +
		       "WHERE dt.id IN :testIds " +
		       "GROUP BY dc.id " +
		       "HAVING COUNT(dt.id) = :testCount")
		List<DiagnosticCenter> findByDiagnosticTestIds(@Param("testIds") List<Integer> testIds,
		                                                 @Param("testCount") long testCount);


	@Query("SELECT dc FROM DiagnosticCenter dc " +
	           "JOIN dc.diagnosticTests dt " +
	           "WHERE dt.testName IN :testNames " +
	           "GROUP BY dc.id")
	    List<DiagnosticCenter> findByDiagnosticTestNames(@Param("testNames") List<String> testNames);

//	@Query(value="select * from diagnostic_center where user_id=:id",nativeQuery = true)
	Optional<DiagnosticCenter> findByUserId(@Param("id") int id);
	
}
