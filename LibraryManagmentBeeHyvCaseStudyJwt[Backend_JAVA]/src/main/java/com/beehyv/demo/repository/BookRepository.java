package com.beehyv.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.beehyv.demo.model.Book;

public interface BookRepository extends JpaRepository< Book , Integer> {

	public Book findByTitle(String title);
    
	//this will return top borrowed books on descending order of total no of students who borrowed this book
	@Query("FROM Book ORDER BY noOfStuBorrowedThisBook DESC")
	public List<Book> findListOfBestBorrowedBooks();
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	
//	@Query("FROM Book WHERE ORDER BY NoOfStuBorrowedThisBook DESC")
//	public List<Book> findBestOfRomanticBorrowedBooks();
//	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	//this will return top borrowed books om desending order of total no of students who borrowed this book per category/subject
	@Query("FROM Book B WHERE B.subject=:name ORDER BY B.noOfStuBorrowedThisBook DESC")
	List<Book> SearchBestBooksBySubject(String name);
	//<----------------------------------------------------------------------------------------------------------------------------->	
	public Book findByAuthors(String author);
	//<----------------------------------------------------------------------------------------------------------------------------->	
	public Book findBySubject(String subject);
	//<----------------------------------------------------------------------------------------------------------------------------->	
	public Book findByIsbn(Integer isbn);
	//<----------------------------------------------------------------------------------------------------------------------------->	
	public Book findByTitleAndAuthors(String title, String author);

}
