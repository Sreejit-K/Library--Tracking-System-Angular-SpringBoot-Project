package com.beehyv.demo.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beehyv.demo.Dto.BookDto;
import com.beehyv.demo.Dto.BookDtoForTrending;
import com.beehyv.demo.model.Book;
import com.beehyv.demo.service.BookService;

import javassist.NotFoundException;

@RestController 
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
	
	@Autowired
	private BookService bookservice;
	
	
	private final ModelMapper modelMapper = new ModelMapper();

//<-------------------------------------------------------------------------------------------------------------------->
	
	//return's the list of allBooks in the Repository 
	@GetMapping("/getbooks")
	public List<BookDto> getAllBooks() throws NotFoundException{	
		
         List<Book> tempBookList = bookservice.getAllBooks();
		
	     BookDto[] bookDtos = modelMapper.map(tempBookList, BookDto[].class) ;
		 return Arrays.asList(bookDtos);	
	}

	
//<-------------------------------------------------------------------------------------------------------------------->
	
	//return's a Book based on its Id in the Repository 
	@GetMapping("/getBook/{Id}")
	public ResponseEntity<BookDto> getBookById(@PathVariable int Id) throws NotFoundException{
		Book book = bookservice.getBookById(Id);
		 BookDto bookDto = modelMapper.map(book, BookDto.class);	 
		return ResponseEntity.ok().body( bookDto);
	}
  
	

//<-------------------------------------------------------------------------------------------------------------------->	

	//implement's adding a book into the repository  
	@PostMapping("/addBook")
	public Book AddBook(@RequestBody Book book) throws Exception{
		
		// extract the title
		String title = book.getTitle();
		//check if title is null 
		if(title != null && !"".equals(title)) {
			Book tempbook = bookservice.getBookByTitle(title);
			
			//check if book already exists in the repository
			//If yes then increase the no of copies of the specified book in the library 
			if(tempbook!=null) {
				int noOfCopies = tempbook.getNoOfCopies();
				
				//increase the no of copies by 1
				noOfCopies++;
				
			    // save this to the temporary book 
				tempbook.setNoOfCopies(noOfCopies);
				
				//now save this temporary to the repository 
				bookservice.saveBook(tempbook);
				
				//now send this message through ecxeption
				throw new Exception("This Book aleady Exists and the has been added sucessfully by increasing the no of copies");
			}	
		}
		
		
		//if the book is not in the current repository then add it into the repository 
		return bookservice.saveBook(book);
		
	}

//<-------------------------------------------------------------------------------------------------------------------->	

	//updates a book in the repository 
	@PutMapping("/updateBook")
	public ResponseEntity<BookDto> updateBook( @Valid @RequestBody Book book) throws NotFoundException {
		return ResponseEntity.ok().body(bookservice.updateBook(book));
	}

//<-------------------------------------------------------------------------------------------------------------------->	
	
	
	//this will make the status as Unavailable if the book is the library
	@DeleteMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable int id) throws Exception {
		return bookservice.deleteBook(id);
		    
	}

//<-------------------------------------------------------------------------------------------------------------------->	
	//will return the bestBorrowed Book 
	@GetMapping("/getBestBooks")
    public List<BookDtoForTrending> getBestBooksBorrowed() throws NotFoundException{
		return bookservice.OrderOfBestBooks();
	}
//<-------------------------------------------------------------------------------------------------------------------->	
	
	//will return the best Book per Category/subject 
	@GetMapping("/getBestSubjectBooks/{name}")
    public List<BookDtoForTrending> getOrderOfBestSubjectBooks(@PathVariable(name = "name", required = true) String name) throws NotFoundException{
		return bookservice.OrderOfBestSubjectBooks(name);
	}


	
//<-------------------------------------------------------------------------------------------------------------------->	
	@GetMapping("/getBookByAuthors/{authors}")
	public Book findByAuthors( @PathVariable(name = "name", required = true) String authors) {	
		return  bookservice.findByAuthors(authors);
	}

	//<-------------------------------------------------------------------------------------------------------------------->		
	
}
