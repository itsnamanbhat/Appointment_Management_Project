package com.graymatter.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.graymatter.entities.User;
import com.graymatter.exceptions.EmailNotFoundException;
import com.graymatter.exceptions.IdNotFoundException;
import com.graymatter.exceptions.InvalidCredentialsException;
import com.graymatter.exceptions.UserOrEmailAlreadyPresent;
import com.graymatter.repositories.UserRepository;

@Repository
public class UserDao {

	@Autowired
	UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User getUserById(int userId) throws IdNotFoundException {
		return repo.findById(userId).orElseThrow(()->new IdNotFoundException("User id: "+userId+" is not present"));
	}

	public User deleteUser(int userId) throws IdNotFoundException {
		User u= repo.findById(userId).orElseThrow(()->new IdNotFoundException("User id: "+ userId+" is not present"));
		repo.deleteById(userId);
		return u;
		
	}

//
	public User addNewUser(User user) throws UserOrEmailAlreadyPresent {
		if (repo.existsByUsername(user.getUsername())) {
            throw new UserOrEmailAlreadyPresent("Username is already taken");
        }
        if (repo.existsByEmail(user.getEmail())) {
            throw new UserOrEmailAlreadyPresent("Email is already in use");
        }
	    return repo.save(user);
	}

	public List<User> getAllUsers() {
		return repo.findAll();
	}
	
	public User findUserByUsername(String userName) {
		return repo.findByUsername(userName).get();
	}

	public User updateUser(int userId, User user) throws IdNotFoundException {
		 User existingUser =repo.findById(userId).orElseThrow(()->new IdNotFoundException("User id: "+userId+" is not present"));
	        // Update fields from UserDto if they are not null
	        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
	            existingUser.setUsername(user.getUsername());
	        }
	        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
	        	String encodedNewPassword = passwordEncoder.encode(user.getPassword());
				existingUser.setPassword(encodedNewPassword);
	            
	        }
	        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
	            existingUser.setEmail(user.getEmail());
	        }

	        // Save the updated user
	        User updatedUser = repo.save(existingUser);

	        return updatedUser;
	    }
		
	public User login(String username, String password) throws InvalidCredentialsException {
        User user = repo.findByUsername(username).get();
        if (user == null) {
            throw new InvalidCredentialsException("Invalid username or password.");
        }

       
        if (!password.equals(user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password.");
        }

        return user;
    }
	
	public User forgotPassword(String email,String password) throws EmailNotFoundException {
		User existingUser= repo.findByEmail(email).orElseThrow(()->new EmailNotFoundException("No email found"));
		 if (password != null ) {
	        	String encodedNewPassword = passwordEncoder.encode(password);
				existingUser.setPassword(encodedNewPassword);
	         }
		 User updatedUser= repo.save(existingUser);
		 return updatedUser;
	}
	
	public User resetPassword(String email,String currentPassword, String newPassword) throws EmailNotFoundException {
		User existingUser= repo.findByEmail(email).orElseThrow(()->new EmailNotFoundException("No email found"));
		String encodedCurrentPassword= passwordEncoder.encode(currentPassword);
//		System.out.println(encodedCurrentPassword);
//		System.out.println(existingUser.getPassword());
		 if (passwordEncoder.matches(currentPassword, existingUser.getPassword()) ) {
	        	String encodedNewPassword = passwordEncoder.encode(newPassword);
				existingUser.setPassword(encodedNewPassword);
				existingUser= repo.save(existingUser);
	         } else {
	        	 throw new EmailNotFoundException("Password Mismatch");
	         }
		 return existingUser;
	}
}

