package com.graymatter.dto;

import java.sql.Date;

import com.graymatter.entities.BookingSlot;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookingSlotDto {

	private int id;
	private int test_id;
	private Date date;
	private int slot;
	

}