package com.graymatter.entities;

import java.sql.Date;
import java.util.*;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date appointmentDate;
    
    @ManyToMany
    @JoinTable(
        name = "appointment_diagnostic_test",
        joinColumns = @JoinColumn(name = "appointment_id"),
        inverseJoinColumns = @JoinColumn(name = "diagnostic_test_id")
    )
    private List<DiagnosticTest> diagnosticTests=new ArrayList<DiagnosticTest>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "diagnostic_center_id")
    private DiagnosticCenter diagnosticCenter;

    @OneToMany(mappedBy = "appointment")
    private List<TestResult> testResults=new ArrayList<TestResult>();
}
