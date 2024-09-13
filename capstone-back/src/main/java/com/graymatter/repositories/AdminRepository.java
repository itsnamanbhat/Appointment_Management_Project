package com.graymatter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graymatter.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
