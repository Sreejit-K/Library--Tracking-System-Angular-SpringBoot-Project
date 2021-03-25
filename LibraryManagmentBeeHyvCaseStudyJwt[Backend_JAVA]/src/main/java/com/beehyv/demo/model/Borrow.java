package com.beehyv.demo.model;


import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Borrow")
public class Borrow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer borrowId;
	
	private Integer bookId;
	private Integer studentId;
	private String bookName;
	private String bookAuthors;
	private String studentName;
	private String studentPhone;
	private Date issueDate;
	private Date dueDate;
	private Date returnDate;
	
	public Borrow() {
		super();
	}

	public Borrow(Integer borrowId, Integer bookId, Integer studentId, String bookName, String bookAuthors,
			String studentName, String studentPhone, Date issueDate, Date dueDate, Date returnDate) {
		super();
		this.borrowId = borrowId;
		this.bookId = bookId;
		this.studentId = studentId;
		this.bookName = bookName;
		this.bookAuthors = bookAuthors;
		this.studentName = studentName;
		this.studentPhone = studentPhone;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
	}
	
	public Borrow( Integer bookId, Integer studentId, String bookName, String bookAuthors,
			String studentName, String studentPhone, Date issueDate, Date dueDate, Date returnDate) {
		super();
		
		this.bookId = bookId;
		this.studentId = studentId;
		this.bookName = bookName;
		this.bookAuthors = bookAuthors;
		this.studentName = studentName;
		this.studentPhone = studentPhone;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
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

	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthors() {
		return bookAuthors;
	}

	public void setBookAuthors(String bookAuthors) {
		this.bookAuthors = bookAuthors;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	@Override
	public String toString() {
		return "Borrow [borrowId=" + borrowId + ", bookId=" + bookId + ", studentId=" + studentId + ", bookName="
				+ bookName + ", bookAuthors=" + bookAuthors + ", studentName=" + studentName + ", studentPhone="
				+ studentPhone + ", issueDate=" + issueDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate
				+ "]";
	}

	
	
	
	
}
