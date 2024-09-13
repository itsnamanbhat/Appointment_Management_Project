package com.graymatter.repositories;

import java.util.Optional;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.graymatter.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

    @Query("SELECT p FROM Patient p WHERE p.user.username = :username")
   List<Patient> findByUsername(@Param("username") String username);

    List<Patient> findByPhoneNo(String phoneNo);
}
