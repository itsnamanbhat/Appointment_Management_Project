package com.graymatter.dto;

import java.util.*;

import com.graymatter.entities.Appointment;
import com.graymatter.entities.CenterAdministrator;
import com.graymatter.entities.DiagnosticTest;
import com.graymatter.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiagnosticCenterDto {
	    private int id;

	    private String name;
	    private String contactNO;
	    private String address;
	    private List<DiagnosticTest> diagnosticTests=new ArrayList<DiagnosticTest>();
	    private List<Appointment> appointments=new ArrayList<Appointment>();
	    private User user;
}
