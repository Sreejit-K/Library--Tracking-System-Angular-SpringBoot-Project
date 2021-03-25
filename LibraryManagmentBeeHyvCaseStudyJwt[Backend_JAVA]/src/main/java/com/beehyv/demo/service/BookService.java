package com.beehyv.demo.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beehyv.demo.Dto.BookDto;
import com.beehyv.demo.Dto.BookDtoForTrending;
import com.beehyv.demo.model.Book;
import com.beehyv.demo.repository.BookRepository;

import javassist.NotFoundException;


@Service
public class BookService {

	//Book Repository
	@Autowired
	private BookRepository bookrepo;
	
	
	//modelMapper
	private final ModelMapper modelMapper = new ModelMapper();
	

//<----------------------------------------------------------------------------------------------------------------------------->	
	
	// Save a Book Functionality
	public Book saveBook(Book book) {
		return bookrepo.save(book);
	}
	
//<----------------------------------------------------------------------------------------------------------------------------->	

	
	
	//Gets the book By Title 
	//And throws an exception if it is not found 
	public Book getBookByTitle(String title) {
		Book book= bookrepo.findByTitle(title);
		
		return book ;
	}
	
//<----------------------------------------------------------------------------------------------------------------------------->	
	
	
	//Gets All the books 
	//And throws an exception if there are no books initially present in the BookRepository
	public List<Book> getAllBooks() throws NotFoundException {
		List<Book> books = bookrepo.findAll();
		
		//exception handling
		// throws an exception if there are no books in the library  
		if (books.size() < 1) {
			throw new NotFoundException("There are no books in the Library");
		}
		
	    return books;
	}

	
//<----------------------------------------------------------------------------------------------------------------------------->	
	
	
	//Gets the book By Id 
	//And throws an exception if it is not found 
	public Book getBookById(int id) throws NotFoundException {
		Book book= bookrepo.findById(id).orElse(null);
		if(book==null) {
			throw new NotFoundException("Book does not exist id : " + id);
		}
		return book ;
	}


//<----------------------------------------------------------------------------------------------------------------------------->
	
	
	// Updating the Book Functionality 
	public BookDto updateBook( Book book) throws NotFoundException {
		
		//get the book and store it in a local variable
        Book tempbook = bookrepo.findById(book.getBookId()).orElse(null) ; 
        
      //exception handling
     // throws an exception if book is not found
        if(tempbook==null) {
			throw new NotFoundException("Book does not exist");
		}
        
        //now set values that we receive from the REST services, to this local variables 
		tempbook.setBookStatus(book.getBookStatus());
		tempbook.setAuthors(book.getAuthors());
		tempbook.setEditionNo(book.getEditionNo());
		tempbook.setIsbn(book.getIsbn());
		tempbook.setLibraryName(book.getLibraryName());
		tempbook.setNoOfCopies(book.getNoOfCopies());
		tempbook.setPublisher(book.getPublisher());
		tempbook.setShelfNo(book.getShelfNo());
		tempbook.setSubject(book.getSubject());
		tempbook.setTitle(book.getTitle());
	    
		//update the book using this local variable
		final Book updatedBook = bookrepo.save(tempbook);
		
		// convert the local variable to BookDto using ModelMapping and return it 
		return modelMapper.map(updatedBook, BookDto.class); 
	}

	
//<----------------------------------------------------------------------------------------------------------------------------->

	//Deleting a book 
	public String deleteBook(int id) throws Exception {
		
		Book book=  bookrepo.findById(id).orElse(null);
		
		//exception handling
		// throws an exception if book is not found
        if(book==null) {
			throw new NotFoundException("Book does not exist with id:" + id);
		}
		
        else {
        	
        	//check if book status is already Unavailable 
        	if(book.getBookStatus()=="UnAvailable") {
        		
        		//if yes return exception 
        		throw new Exception("Book Status is already made Unavilable");
        	}
        	
        	//else make it unavailable 
        	//set book status to unavailable when deleted   
    		book.setBookStatus("UnAvailable");
    		//now update this to the Repository 
    		bookrepo.save(book);
    		
    		//now return the message 
    		return "Book has been made unavailable with id : " + id;
        	
        }
        		
        
       
	}
	

//<----------------------------------------------------------------------------------------------------------------------------->	
	
	
	//Function to get the Best Borrowed Books List 
	public List<BookDtoForTrending> OrderOfBestBooks() throws NotFoundException{
		List<Book> tempBook = bookrepo.findListOfBestBorrowedBooks();
		

		//exception handling
		// throws an exception if there are no Best books Record in the library  
		if (tempBook.size() < 1) {
			throw new NotFoundException("There are no books in the Library");
		}
		
		//use model mapping to convert the the book entity list to an BookDto Array 
		BookDtoForTrending[] bookDtos = modelMapper.map(tempBook, BookDtoForTrending[].class);	
		
		//return the list 
		return Arrays.asList(bookDtos);
		
	}
	

//<----------------------------------------------------------------------------------------------------------------------------->	
	
	
	//Function to get the Best Borrowed Books List per Subject 
	public List<BookDtoForTrending> OrderOfBestSubjectBooks(String subject) throws NotFoundException{
		
		List<Book> tempBook = bookrepo.SearchBestBooksBySubject(subject);
		

		//exception handling
		// throws an exception if there are no Best books Record in the library  
		if (tempBook.size() < 1) {
			throw new NotFoundException("There are no books in the Library with subject:"+ subject);
		}
		
		//use model mapping to convert the the book entity list to an BookDto Array
		BookDtoForTrending[] bookDtos = modelMapper.map(tempBook, BookDtoForTrending[].class) ;
		
		//return the list 
		return Arrays.asList(bookDtos);	
	}

	
	
//<----------------------------------------------------------------------------------------------------------------------------->	
	public Book findByAuthors(String authors) {
		Book book = bookrepo.findByAuthors(authors);
		return book;
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->		

}
