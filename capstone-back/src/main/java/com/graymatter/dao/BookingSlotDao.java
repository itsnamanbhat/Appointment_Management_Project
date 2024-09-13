package com.graymatter.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.graymatter.entities.BookingSlot;
import com.graymatter.exceptions.ConflictException;
import com.graymatter.repositories.BookingSlotRepository;

@Repository
public class BookingSlotDao {
	@Autowired
	BookingSlotRepository repo;
	
	public BookingSlot addSlot(BookingSlot b) throws ConflictException {
		  BookingSlot existingSlot = repo.findByTestIdAndDate(b.getTest_id(), b.getDate());
	        
	        if (existingSlot != null) {
	            // Update the existing slot
	            existingSlot.setSlot(b.getSlot());
	            return repo.save(existingSlot);
	        } else {
	            // Insert new slot
	            return repo.save(b);
	        }
		
	}
	public int getSlotOfTest(int test_id,Date date) {
		return repo.getSlotOfTest(test_id,date);
		
	}
	public void updateSlotsForTests(List<Integer> testIds, Date date) {
		repo.updateSlotsForTests(testIds,date);
		
	} 
	public List<Integer> availabletestIds(Date date) {
		return repo.availableTesIds(date);
		
	}
	public List<BookingSlot> getBookingSlotOfTest(int id) {
		// TODO Auto-generated method stub
		return repo.findByTest_id(id);
	}
	

}
