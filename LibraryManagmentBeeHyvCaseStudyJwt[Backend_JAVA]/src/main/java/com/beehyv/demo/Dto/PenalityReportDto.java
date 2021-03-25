package com.beehyv.demo.Dto;

import java.sql.Date;

public class PenalityReportDto {
	//BorrowDetails
	
    private Integer borrowId;	
	private Integer randomTokenGenrator;
	private Integer bookId;
	private Integer studentId;
	private Date issueDate;
	private Date dueDate;
	private Date returnDate;
	
	//book_Details
	private String subject;
	private String authors;
	private String title ;
	private String publisher;
	private Integer editionNo;
	private int isbn;
	private int shelfNo;
	private String libraryName;
	
	//StudentDetails
	private String name;
	private String email;
	private String department;
	private String phone;
   
	//Fine
	private Integer fine;
	private Integer no_of_days;
	private String message;
	
	
	public PenalityReportDto() {
		super();
	}


	  public PenalityReportDto(Integer borrowId, Integer randomTokenGenrator, Integer bookId, Integer studentId,
			Date issueDate, Date dueDate, Date returnDate, String subject, String authors, String title,
			String publisher, Integer editionNo, int isbn, int shelfNo, String libraryName, String name, String email,
			String department, String phone, Integer fine, Integer no_of_days, String message) {
		super();
		this.borrowId = borrowId;
		this.randomTokenGenrator = randomTokenGenrator;
		this.bookId = bookId;
		this.studentId = studentId;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.subject = subject;
		this.authors = authors;
		this.title = title;
		this.publisher = publisher;
		this.editionNo = editionNo;
		this.isbn = isbn;
		this.shelfNo = shelfNo;
		this.libraryName = libraryName;
		this.name = name;
		this.email = email;
		this.department = department;
		this.phone = phone;
		this.fine = fine;
		this.no_of_days = no_of_days;
		this.message = message;
	}







	public Integer getRandomTokenGenrator() {
			return randomTokenGenrator;
		}

		public void setRandomTokenGenrator(Integer randomTokenGenrator) {
			this.randomTokenGenrator = randomTokenGenrator;
		}



	public String getAuthors() {
		return authors;
	}



	public void setAuthors(String authors) {
		this.authors = authors;
	}


	public int getIsbn() {
		return isbn;
	}



	public void setIsbn(int isbn) {
		this.isbn = isbn;
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



	public Integer getBorrowId() {
		return borrowId;
	}


	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}


	public Integer getBookId() {
		return bookId;
	}


	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}


	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


	public Date getIssueDate() {
		return issueDate;
	}


	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
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


	public Integer getEditionNo() {
		return editionNo;
	}


	public void setEditionNo(Integer editionNo) {
		this.editionNo = editionNo;
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


	public Integer getFine() {
		return fine;
	}


	public void setFine(Integer fine) {
		this.fine = fine;
	}


	public Integer getNo_of_days() {
		return no_of_days;
	}


	public void setNo_of_days(Integer no_of_days) {
		this.no_of_days = no_of_days;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "PenalityReportDto [borrowId=" + borrowId + ", randomTokenGenrator=" + randomTokenGenrator + ", bookId="
				+ bookId + ", studentId=" + studentId + ", issueDate=" + issueDate + ", dueDate=" + dueDate
				+ ", returnDate=" + returnDate + ", subject=" + subject + ", authors=" + authors + ", title=" + title
				+ ", publisher=" + publisher + ", editionNo=" + editionNo + ", isbn=" + isbn + ", shelfNo=" + shelfNo
				+ ", libraryName=" + libraryName + ", name=" + name + ", email=" + email + ", department=" + department
				+ ", phone=" + phone + ", fine=" + fine + ", no_of_days=" + no_of_days + ", message=" + message + "]";
	}


	

	
	
}
