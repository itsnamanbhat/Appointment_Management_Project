	package com.graymatter.entities;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DiagnosticTest {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String testName;
	private double testPrice;

	@ManyToOne
    @JoinColumn(name = "diagnostic_center_id")
	@JsonIgnore
    private DiagnosticCenter diagnosticCenter;
	
	@ManyToMany(mappedBy = "diagnosticTests")
	@JsonIgnore
    private List<Appointment> appointments=new ArrayList<Appointment>();

}
