package com.beehyv.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beehyv.demo.model.Student;

public interface StudentRepository extends JpaRepository< Student, Integer>{

	public Student findByName(String username);

	public Student findByNameAndEmail(String name, String email);

	public Student findByEmail(String email);

	public Student findByPhone(String phone);
}
