package com.graymatter.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TestResult {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String testName;
	
	@ManyToOne
	@JoinColumn(name="appointmentId")
	@JsonIgnore
	private Appointment appointment;
}
