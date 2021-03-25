package com.beehyv.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


//STUDENT(backend) or MEMBER(frontend) Entity  are both the same 

@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;
	private String name;
	private String email;
    private String password;
	private String department;
	private String phone;
	private String status ;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name="student_Book", 
			joinColumns = {@JoinColumn(name="studentId")},
			inverseJoinColumns = {@JoinColumn(name="bookId")})
	Set<Book> books = new HashSet<>();


	
	public Student() {
		super();
	}


	


	public Student(int studentId, String name, String email, String password, String department, String phone,
			String status, Set<Book> books) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.department = department;
		this.phone = phone;
		this.status = status;
		this.books = books;
	}


	public Student( String name, String email, String password, String department, String phone,
			String status) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.department = department;
		this.phone = phone;
		this.status = status;
	}




	public Student(int studentId, String name, String email, String password, String department, String phone,
			String status) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.department = department;
		this.phone = phone;
		this.status = status;
	}



	public int getStudentId() {
		return studentId;
	}


	public void setStudentId(int studentId) {
		this.studentId = studentId;
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


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Set<Book> getBooks() {
		return books;
	}


	public void setBooks(Set<Book> books) {
		this.books = books;
	}


	public void addNewBook(Book book) {
		books.add(book);
		
	}
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}




	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", department=" + department + ", phone=" + phone + ", status=" + status + ", books=" + books + "]";
	}




	
}
	