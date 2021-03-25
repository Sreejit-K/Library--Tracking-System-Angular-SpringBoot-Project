package com.beehyv.demo.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;




@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	
	private String subject;
	private String authors;
	private String title ;
	private String publisher;
	private int editionNo;
	private int isbn;
	private int noOfCopies;
	private int shelfNo;
	private String libraryName;
	private String bookStatus;
	
	
	@ManyToMany(mappedBy = "books",fetch = FetchType.LAZY)
	Set<Student> studentsWhoBooked;
	
	private int noOfStuBorrowedThisBook   ;
	
	
	
	
	public Book() {
		super();
	}






	public Book(int bookId, String subject, String authors, String title, String publisher, int editionNo, int isbn,
			int noOfCopies, int shelfNo, String libraryName, String bookStatus, Set<Student> studentsWhoBooked,
			int noOfStuBorrowedThisBook) {
		super();
		this.bookId = bookId;
		this.subject = subject;
		this.authors = authors;
		this.title = title;
		this.publisher = publisher;
		this.editionNo = editionNo;
		this.isbn = isbn;
		this.noOfCopies = noOfCopies;
		this.shelfNo = shelfNo;
		this.libraryName = libraryName;
		this.bookStatus = bookStatus;
		this.studentsWhoBooked = studentsWhoBooked;
		this.noOfStuBorrowedThisBook = noOfStuBorrowedThisBook;
	}



	public Book(int bookId, String subject, String authors, String title, String publisher, int editionNo, int isbn,
			int noOfCopies, int shelfNo, String libraryName, String bookStatus, int noOfStuBorrowedThisBook) {
		super();
		this.bookId = bookId;
		this.subject = subject;
		this.authors = authors;
		this.title = title;
		this.publisher = publisher;
		this.editionNo = editionNo;
		this.isbn = isbn;
		this.noOfCopies = noOfCopies;
		this.shelfNo = shelfNo;
		this.libraryName = libraryName;
		this.bookStatus = bookStatus;
		this.noOfStuBorrowedThisBook = noOfStuBorrowedThisBook;
	}

	
	public Book( String subject, String authors, String title, String publisher, int editionNo, int isbn,
			int noOfCopies, int shelfNo, String libraryName, String bookStatus, int noOfStuBorrowedThisBook) {
		super();
		
		this.subject = subject;
		this.authors = authors;
		this.title = title;
		this.publisher = publisher;
		this.editionNo = editionNo;
		this.isbn = isbn;
		this.noOfCopies = noOfCopies;
		this.shelfNo = shelfNo;
		this.libraryName = libraryName;
		this.bookStatus = bookStatus;
		this.noOfStuBorrowedThisBook = noOfStuBorrowedThisBook;
	}

	public int getBookId() {
		return bookId;
	}






	public void setBookId(int bookId) {
		this.bookId = bookId;
	}






	public String getSubject() {
		return subject;
	}






	public void setSubject(String subject) {
		this.subject = subject;
	}






	public String getAuthors() {
		return authors;
	}






	public void setAuthors(String authors) {
		this.authors = authors;
	}






	public String getTitle() {
		return title;
	}






	public void setTitle(String title) {
		this.title = title;
	}






	public String getPublisher() {
		return publisher;
	}






	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}






	public int getEditionNo() {
		return editionNo;
	}






	public void setEditionNo(int editionNo) {
		this.editionNo = editionNo;
	}






	public int getIsbn() {
		return isbn;
	}






	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}






	public int getNoOfCopies() {
		return noOfCopies;
	}






	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}






	public int getShelfNo() {
		return shelfNo;
	}






	public void setShelfNo(int shelfNo) {
		this.shelfNo = shelfNo;
	}






	public String getLibraryName() {
		return libraryName;
	}






	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}






	public String getBookStatus() {
		return bookStatus;
	}






	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}






	public Set<Student> getStudentsWhoBooked() {
		return studentsWhoBooked;
	}






	public void setStudentsWhoBooked(Set<Student> studentsWhoBooked) {
		this.studentsWhoBooked = studentsWhoBooked;
	}






	public int getNoOfStuBorrowedThisBook() {
		return noOfStuBorrowedThisBook;
	}






	public void setNoOfStuBorrowedThisBook(int noOfStuBorrowedThisBook) {
		this.noOfStuBorrowedThisBook = noOfStuBorrowedThisBook;
	}






	public void addStudent(Student student) {
		studentsWhoBooked.add(student);
	}
	
	public void removeStudent(Student student) {
		studentsWhoBooked.remove(student);
	}

	
	
}
