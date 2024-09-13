package com.graymatter.dto;

import com.graymatter.entities.DiagnosticCenter;
import com.graymatter.entities.User;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenterAdministratorDto {

		private int id;
		private String name;
		private String phoneNo;
	    private String address;
	    
		private DiagnosticCenter diagnosticCenter;
		
		private User user;

	}

