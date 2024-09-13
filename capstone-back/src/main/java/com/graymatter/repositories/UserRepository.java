package com.graymatter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graymatter.entities.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String userName);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
}
