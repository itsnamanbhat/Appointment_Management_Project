package com.graymatter.dto;

import java.util.*;

import com.graymatter.entities.Appointment;
import com.graymatter.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientDto {
	private int id;
	private String name;
	private String phoneNo;
	private int age;
	private String gender;
	private String aadharNumber;
	
	private List<Appointment> appointments=new ArrayList<Appointment>();
	private User user;
}
