package com.graymatter.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graymatter.entities.BookingSlot;
import com.graymatter.exceptions.ConflictException;
import com.graymatter.services.BookingSlotService;

@RestController
@RequestMapping("/api/v1/")
public class BookingSlotController {
	
	@Autowired
	BookingSlotService service;
	
	@PostMapping("/booking")
	public BookingSlot addSlot(@RequestBody BookingSlot b) throws ConflictException {
		return service.addSlot(b);
		
	}
	
	@GetMapping("/booking/test/{id}")
		public List<BookingSlot> getBookingSlotOfCenter(@PathVariable int id) {
			return service.getBookingSlotOfTest(id);
		}
	
	
	@GetMapping("/boooking/slot/id/{test_id}/date/{date}")
	public int getSlotOfCenter(@PathVariable int test_id,@PathVariable Date date) {
		return service.getSlotOfTest(test_id,date);
		
	}
	
//	@PutMapping("/booking/slot/{test_id}/date/{date}")
//	public void updateSlot(@PathVariable int test_id,@PathVariable Date date) {
//		 service.updateSlot(test_id,date);
//		
//	} 
	
	@GetMapping("/booking/date/{date}")
	public List<Integer> availableCenterIds(@PathVariable Date date) {
		return service.availableTestIds(date);
		
	}

}
