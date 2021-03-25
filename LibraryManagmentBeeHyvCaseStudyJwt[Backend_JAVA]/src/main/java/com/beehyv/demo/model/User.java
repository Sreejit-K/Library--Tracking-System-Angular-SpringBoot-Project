package com.beehyv.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//USER(backend) or LIBRARIAN(frontend) Entity  are both the same 
@Entity
public class User {
	
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id ;
 
 private String username;
 private String name;
 private String email;
 private String password ;
 private String phone;
 private String userStatus;
 

public User() {
	super();
}



public User(int id, String username, String name, String email, String password, String phone, String userStatus) {
	super();
	this.id = id;
	this.username = username;
	this.name = name;
	this.email = email;
	this.password = password;
	this.phone = phone;
	this.userStatus = userStatus;
}



public String getPhone() {
	return phone;
}


public void setPhone(String phone) {
	this.phone = phone;
}






public User(String  username, String name, String  email, String password) {

	this.username = username;
	this.name = name;
	this.email = email;
	this.password = password;
}



public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}


public String getUserStatus() {
	return userStatus;
}


public void setUserStatus(String userStatus) {
	this.userStatus = userStatus;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", name=" + name + ", email=" + email + ", password="
			+ password + ", phone=" + phone + ", userStatus=" + userStatus + "]";
}










}
