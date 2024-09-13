package com.graymatter.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graymatter.dao.BookingSlotDao;
import com.graymatter.entities.BookingSlot;
import com.graymatter.exceptions.ConflictException;

@Service
public class BookingSlotService {
	
	@Autowired
	BookingSlotDao dao;
	public BookingSlot addSlot(BookingSlot b) throws ConflictException {
		return dao.addSlot(b);
		
	}
	public int getSlotOfTest(int center_id,Date date) {
		return dao.getSlotOfTest(center_id,date);
		
	}
	public List<Integer> availableTestIds(Date date) {
		return dao.availabletestIds(date);
		
	}
	public List<BookingSlot> getBookingSlotOfTest(int id) {
		// TODO Auto-generated method stub
		return dao.getBookingSlotOfTest(id);
	}
	

}
