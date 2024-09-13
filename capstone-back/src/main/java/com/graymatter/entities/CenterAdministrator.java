package com.graymatter.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CenterAdministrator {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String phoneNo;
    private String address;
    
    
//    @OneToOne(mappedBy = "centerAdmin", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
//	private DiagnosticCenter diagnosticCenter;
	
//	@OneToOne
//	@JoinColumn(name="userId",referencedColumnName = "id")
//	private User user;

}
