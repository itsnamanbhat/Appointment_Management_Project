package com.graymatter.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.graymatter.entities.BookingSlot;

import jakarta.transaction.Transactional;

public interface BookingSlotRepository extends JpaRepository<BookingSlot, Integer>{

	 @Query("SELECT DISTINCT b.test_id FROM BookingSlot b WHERE b.date = :date AND b.slot > 0")
	   List<Integer> availableTesIds(@Param("date") Date date);
	 
	 @Query("SELECT b.slot FROM BookingSlot b WHERE b.test_id = :testId AND b.date = :date")
	    int getSlotOfTest(@Param("testId") int testId, @Param("date") Date date);

	 
	 @Modifying
	 @Transactional
	 @Query("UPDATE BookingSlot b SET b.slot = b.slot - 1 WHERE b.test_id IN :testIds AND b.date = :date AND b.slot > 0")
	 void updateSlotsForTests(@Param("testIds") List<Integer> testIds, @Param("date") Date date);

	    

	    @Query("SELECT COUNT(b) > 0 FROM BookingSlot b WHERE b.test_id = :test_id AND b.date = :date")
	    boolean existsByTestIdAndDate(@Param("test_id") int test_id, @Param("date") Date date);
	    
	    @Query("select b from BookingSlot b where b.test_id=:id")
	    List<BookingSlot> findByTest_id(@Param("id")int id);

	    @Query(value="Select * from booking_slot where test_id=:test_id AND date=:date",nativeQuery = true)
		BookingSlot findByTestIdAndDate(int test_id, Date date);

	    
}
