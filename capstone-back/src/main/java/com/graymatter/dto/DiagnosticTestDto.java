package com.graymatter.dto;

import java.util.*;

import com.graymatter.entities.Appointment;
import com.graymatter.entities.DiagnosticCenter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticTestDto {

	private int id;
	
	@NotNull(message = "Test Name cannot be null")
    @Size(min = 1, max = 100, message = "Test Name must be between 1 and 100 characters")
	private String testName;
	
	@NotNull(message = "Test price cannot be null")
	@Positive(message = "Test price must be positive")
	private double testPrice;
	
	 
	@NotNull(message = "Diagnostic Centers cannot be null")
	@Size(min = 1, message = "At least one diagnostic center is required")
	private DiagnosticCenter diagnosticCenter;
	
    private List<Appointment> appointments=new ArrayList<Appointment>();
}
