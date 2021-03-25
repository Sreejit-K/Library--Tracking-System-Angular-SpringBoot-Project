package com.beehyv.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.beehyv.demo.model.User;

public interface UserRepository extends JpaRepository< User, Integer> {

	public User findByEmail(String email);

	public User findByEmailAndPassword(String email, String password);

	public User findByName(String name);

	public User findByUsername(String username);
	
	
}
