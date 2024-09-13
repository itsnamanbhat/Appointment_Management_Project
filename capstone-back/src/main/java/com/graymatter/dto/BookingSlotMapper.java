package com.graymatter.dto;

import org.springframework.stereotype.Component;

import com.graymatter.entities.BookingSlot;

@Component
public class BookingSlotMapper {

	public BookingSlotDto mapToBookingDto(BookingSlot slot) {
		return new BookingSlotDto(slot.getId(),slot.getTest_id(),slot.getDate(),slot.getSlot());
	}
	public BookingSlot mapToBooking(BookingSlot slot) {
		return new BookingSlot(slot.getId(),slot.getTest_id(),slot.getDate(),slot.getSlot());
	}

}
