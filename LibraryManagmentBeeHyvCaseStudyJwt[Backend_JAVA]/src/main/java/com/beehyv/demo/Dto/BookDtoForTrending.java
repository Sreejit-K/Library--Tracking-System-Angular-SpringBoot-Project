package com.beehyv.demo.Dto;

public class BookDtoForTrending {
	private int bookId;
	private String authors;
	private String subject;
	private String title ;
	private String publisher;
	private int editionNo;
	private int isbn;
	private int noOfCopies;
	private int shelfNo;
	private String libraryName;
	private String bookStatus;
	private int noOfStuBorrowedThisBook ;
	
	public BookDtoForTrending() {
		super();
	}

	public BookDtoForTrending(int bookId, String authors, String subject, String title, String publisher, int editionNo,
			int isbn, int noOfCopies, int shelfNo, String libraryName, String bookStatus, int noOfStuBorrowedThisBook) {
		super();
		this.bookId = bookId;
		this.authors = authors;
		this.subject = subject;
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

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
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

	public int getNoOfStuBorrowedThisBook() {
		return noOfStuBorrowedThisBook;
	}

	public void setNoOfStuBorrowedThisBook(int noOfStuBorrowedThisBook) {
		this.noOfStuBorrowedThisBook = noOfStuBorrowedThisBook;
	}

	@Override
	public String toString() {
		return "BookDtoForTrending [bookId=" + bookId + ", authors=" + authors + ", subject=" + subject + ", title="
				+ title + ", publisher=" + publisher + ", editionNo=" + editionNo + ", isbn=" + isbn + ", noOfCopies="
				+ noOfCopies + ", shelfNo=" + shelfNo + ", libraryName=" + libraryName + ", bookStatus=" + bookStatus
				+ ", noOfStuBorrowedThisBook=" + noOfStuBorrowedThisBook + "]";
	}
	
    
}
