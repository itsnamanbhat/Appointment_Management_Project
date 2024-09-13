package com.graymatter.entities;

import java.sql.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookingSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int test_id;
	private Date date;
	private int slot;
	

}
