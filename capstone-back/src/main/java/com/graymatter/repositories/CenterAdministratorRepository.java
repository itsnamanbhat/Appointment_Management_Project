package com.graymatter.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graymatter.entities.CenterAdministrator;

public interface CenterAdministratorRepository extends JpaRepository<CenterAdministrator,Integer>{

	
//	Optional<CenterAdministrator> findByUserId(int userId);

}
