package com.beehyv.demo.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.beehyv.demo.model.User;
import com.beehyv.demo.service.UserService;

import javassist.NotFoundException;


//USER(backend) or LIBRARIAN(frontend) Entity  are both the same 

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	
	@Autowired
	private UserService service;
//<-------------------------------------------------------------------------------------------------------------------->	
	
	@PostMapping("/addUser")
	@CrossOrigin(origins = "http://localhost:4200")
    public User AddNewUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmail();
		if(tempEmailId!=null && !"".equals(tempEmailId)) {
			User userObj = service.fetchUserByEmailId(tempEmailId);
			if(userObj!= null) {
				throw new Exception("User already Exists");
			}
		}
		
    	
    	return service.saveUser(user);
	}
//<-------------------------------------------------------------------------------------------------------------------->	
	//this was used before implementing Spring JWT authentication
	@PostMapping("/login")
	
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmail();
		String tempPassword = user.getPassword();
		User userObj = null;
		
		if(tempEmailId!=null && tempPassword!= null) {
			 userObj= service.fetchUserByEmailAndPassword(tempEmailId, tempPassword);
		}
		if(userObj==null) {
			throw new Exception("Bad Credentials");
		}
		return userObj;
	}

//<-------------------------------------------------------------------------------------------------------------------->	
	
	
	@GetMapping("/getUsers")
	public List<User> getAllUserForLibrarian() throws NotFoundException{
		return service.getAllUsers();
		
	}
//<----------------------------------------------------------------------------------------------------------------------------->	
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> findUserById(@PathVariable int id) throws NotFoundException   {
		 User user = service.getUserById(id);
		 
		return ResponseEntity.ok().body(user);
	}
//<----------------------------------------------------------------------------------------------------------------------------->	
	
	
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser( @Valid @RequestBody User user) throws NotFoundException {
		
		return ResponseEntity.ok().body(service.updateUser(user));
	}
//<----------------------------------------------------------------------------------------------------------------------------->	
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) throws Exception {
		return service.deleteUser(id);
	}
	
//<----------------------------------------------------------------------------------------------------------------------------->	
	  
	
}
