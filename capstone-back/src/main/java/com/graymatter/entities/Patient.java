package com.graymatter.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Column(unique = true)
	private String phoneNo;
	private int age;
	private String gender;
	private String aadharNumber;
	
	 
	@JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<Appointment> appointments=new ArrayList<Appointment>();
	
	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName = "id")
	private User user;
}	
