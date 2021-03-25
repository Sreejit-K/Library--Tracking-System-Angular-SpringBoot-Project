package com.beehyv.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beehyv.demo.model.User;
import com.beehyv.demo.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class UserService {
  
	@Autowired
	private UserRepository repo; 
	//<----------------------------------------------------------------------------------------------------------------------------->	
	public User saveUser(User user) {	
		return repo.save(user);
	}
	//<----------------------------------------------------------------------------------------------------------------------------->
	public User fetchUserByEmailId(String email) {
		
		return repo.findByEmail(email);
	}
	//<----------------------------------------------------------------------------------------------------------------------------->
    public User fetchUserByEmailAndPassword(String email, String password) {
		
		return repo.findByEmailAndPassword(email, password);
	}
    //<----------------------------------------------------------------------------------------------------------------------------->
	public User getUserByUsername(String username) {
		return repo.findByUsername(username);
	}
	//<----------------------------------------------------------------------------------------------------------------------------->
	public List<User> getAllUsers() throws NotFoundException {
		List<User> users = repo.findAll();
		//exception handling
		// throws an exception if there are no books in the library  
		if (users.size() < 1) {
			throw new NotFoundException("There are no users in the Library");
		}
		
		 return users;
		
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->
	//Deleting a Student/Member 
	public String deleteUser(int id) throws Exception {
		User user=  repo.findById(id).orElse(null);
		
		//exception handling
		// throws an exception if Student/Member is not found
		if(user==null) {
			throw new NotFoundException("Student does not exist id : " + id);
		}
		else {
			
		//check if Student/Member status is already Inactive 
    	if(user.getUserStatus() =="InActive") {
    		
    	    //if yes return exception 
    		throw new Exception("Book Status is already made Unavilable");
    	}
    	
    	//else make it Inactive 
    	//set Student status to Inactive when deleted 
    	user.setUserStatus("InActive");
		
		//now update this to the Repository 
		repo.save(user);
		
		//now return the message 
		return "Librarian has been made inactive with id : " + id;
		}
	}

	//<----------------------------------------------------------------------------------------------------------------------------->
	
	//Updating the Student Functionality 
	public User updateUser( User user) throws NotFoundException {
		//get the Student and store it in a local variable
		User existingUser = repo.findById(user.getId()).orElse(null);
		
		 //exception handling
	     // throws an exception if student is not found
		if(existingUser==null) {
			throw new NotFoundException("Student does not exist id : " +user.getId());
		}
		
		//now set values that we receive from the REST services, to this local variables 
		existingUser.setName(user.getName());
		existingUser.setPassword(user.getPassword());
		existingUser.setEmail(user.getEmail());
		existingUser.setUsername(user.getUsername());
		existingUser.setPhone(user.getPhone());
		existingUser.setUserStatus(user.getUserStatus());
		
		//update the book using this local variable
		final User updatedUser = repo.save(existingUser);
		
		// convert the local variable to StudentDto using ModelMapping and return it 
		return updatedUser;
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->
	//<----------------------------------------------------------------------------------------------------------------------------->
	

	//Gets the Student By Id 
	//And throws an exception if it is not found 
	public User getUserById(int id) throws NotFoundException {
		User user = repo.findById(id).orElse(null);
		
		//exception handling
		// throws an exception if book is not found
		if(user==null) {
			throw new NotFoundException("User does not exist id : " + id);
		}
		return user ;
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->
	
}
