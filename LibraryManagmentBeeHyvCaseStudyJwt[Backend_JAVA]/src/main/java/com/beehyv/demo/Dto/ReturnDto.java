package com.beehyv.demo.Dto;

import java.sql.Date;

public class ReturnDto {
	private int bookId;
	private int studentId;
	private int borrowId;
	private Date returnDate;
	
	public ReturnDto() {
		super();
	}
	
	
	
	public ReturnDto(int bookId, int studentId, int borrowId, Date returnDate) {
		super();
		this.bookId = bookId;
		this.studentId = studentId;
		this.borrowId = borrowId;
		this.returnDate = returnDate;
	}
	
	public int getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	
	@Override
	public String toString() {
		return "ReturnDto [bookId=" + bookId + ", studentId=" + studentId + ", borrowId=" + borrowId + ", returnDate="
				+ returnDate + "]";
	}
	
	
	

	

}
