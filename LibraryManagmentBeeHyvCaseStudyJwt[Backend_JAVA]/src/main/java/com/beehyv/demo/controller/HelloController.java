package com.beehyv.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beehyv.demo.Dto.AuthenticationRequest;
import com.beehyv.demo.Dto.AuthenticationResponse;
import com.beehyv.demo.model.User;
import com.beehyv.demo.security.JwtUtil;
import com.beehyv.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtTokenUtil ;
	
	
	// this controller is used for spring security testing
	// secutity login 
	
	//<-------------------------------------------------------------------------------------------------------------------->	
	
	@RequestMapping({"/hello"})
	public String hello() {
		return "HelloWorld";
	}
	//<-------------------------------------------------------------------------------------------------------------------->	
	
	// login API using spring security JWT
	@RequestMapping(value="/authentication", method =RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
		try {
		       authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())	
				);
		       
		}catch(BadCredentialsException e) {
			throw new Exception("Incorrect Username or Password ", e);
		}
		
		
		final User user = userService.getUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(user);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	} 
	
	//<-------------------------------------------------------------------------------------------------------------------->	
	

}
