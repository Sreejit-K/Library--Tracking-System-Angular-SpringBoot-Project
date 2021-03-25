package com.beehyv.demo.Dto;

public class StudentDto {
	private int studentId;
	private String name;
	private String email;
	private String department;
	private String phone;
	private String status;
	private String password;
	
	
	
	
	public StudentDto() {
		super();
	}
	
	public StudentDto(int studentId, String name, String email, String department, String phone, String status,
			String password) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.department = department;
		this.phone = phone;
		this.status = status;
		this.password = password;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "StudentDto [studentId=" + studentId + ", name=" + name + ", email=" + email + ", department="
				+ department + ", phone=" + phone + ", status=" + status + ", password=" + password + "]";
	}
	
	
	
}
