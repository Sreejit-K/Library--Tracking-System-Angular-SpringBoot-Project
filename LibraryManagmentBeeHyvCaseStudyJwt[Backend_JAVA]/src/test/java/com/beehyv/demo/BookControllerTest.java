package com.beehyv.demo;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Assertions;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.beehyv.demo.Dto.BookDto;
import com.beehyv.demo.Dto.BookDtoForTrending;

import com.beehyv.demo.controller.BookController;

import com.beehyv.demo.model.Book;

import com.beehyv.demo.repository.BookRepository;
import com.beehyv.demo.service.BookService;

import javassist.NotFoundException;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BookControllerTest {
	

	@Autowired
	private BookController controller;
	
	@Autowired
	private BookService service;
	
	@Autowired
	private BookRepository repo;
	
	//---------------------------------------------------------------------------------------------	
		@Test
		@Rollback(false)
		@Order(1)
		public void testgetAllBooks()  {
			

				try {
					List<BookDto> books  = controller.getAllBooks();
					
					for(BookDto  book:  books ) {
						System.out.println(book);
					}
					assertThat( books ).size().isGreaterThan(0);
					

				} catch (NotFoundException e) {
					 Assertions.fail("Exception " + e);
			
				}
				
		
		}
		
	//---------------------------------------------------------------------------------------------	

	//---------------------------------------------------------------------------------------------	
		

		@Test
		@Rollback(false)
		@Order(3)
		public void testAddBook()  {
			
				try {
					Book book = new Book();
					book.setSubject("FantasyBooks");
					book.setAuthors("heamsworth");
					book.setTitle("Twilight");
					book.setPublisher("ComiconLimited");
					book.setIsbn(12);
					book.setEditionNo(34);
					book.setShelfNo(9);
					book.setNoOfCopies(56);
					book.setNoOfStuBorrowedThisBook(89);
					book.setBookStatus("OutOfStock");
					book.setLibraryName("BeehyvLtd");
					
					Book  bookSaved= controller.AddBook(book) ;
				
				   assert(bookSaved) != null;

				} catch (Exception e) {
					 Assertions.fail("Exception " + e);
			
				}
				
		
		}
		
	//---------------------------------------------------------------------------------------------	

		@Test
		@Rollback(false)
		@Order(4)
		public void updateBook()  {
			
				try {
					String title = "Twilight";
					String authors= "heamsworth";
					Book book = controller.findByAuthors(authors);
					
					book.setTitle(title);
					
					
					
					
					BookDto  UpdatedBook= service.updateBook(book) ;
					
					assertThat(UpdatedBook.getTitle()).isEqualTo(title);

				} catch (Exception e) {
					 Assertions.fail("Exception " + e);
			
				}
				
		
		}
		
	//---------------------------------------------------------------------------------------------	
		
		@Test
		@Rollback(false)
		@Order(5)
		public void deletBook()  {
			
				try {
					String title= "Twilight" ;
					int id = repo.findByTitle(title).getBookId();
					String messageOnDeleting = controller.deleteBook(id);
					
					assertThat(messageOnDeleting).isEqualTo("Book has been made unavailable with id : " + id);
							

				} catch (Exception e) {
					 Assertions.fail("Exception " + e);
			
				}
				
		
		}
		
   //---------------------------------------------------------------------------------------------	
		@Test
		@Rollback(false)
		@Order(6)
		public void testTrendingBooks()  {
			

				try {
					List<BookDtoForTrending> books  = controller.getBestBooksBorrowed();
					
					for(BookDtoForTrending  book:  books ) {
						System.out.println(book);
					}
					assertThat( books ).size().isGreaterThan(0);
					

				} catch (NotFoundException e) {
					 Assertions.fail("Exception " + e);
			
				}
				
		
		}
		
	//---------------------------------------------------------------------------------------------	
		@Test
		@Rollback(false)
		@Order(7)
		public void testTrendingBooksPerCatogery()  {
			

				try {
					String category = "FantasyBooks";
					List<BookDtoForTrending> books  = controller.getOrderOfBestSubjectBooks(category);
					
					for(BookDtoForTrending  book:  books ) {
						System.out.println(book);
					}
					assertThat( books ).size().isGreaterThan(0);
					

				} catch (NotFoundException e) {
					 Assertions.fail("Exception " + e);
			
				}
				
		
		}
		
	//---------------------------------------------------------------------------------------------	
		@Test
		@Rollback(false)
		@Order(8)
		public void testDeleteBook() {
			String title= "Twilight" ;
			int id = repo.findByTitle(title).getBookId();
			boolean beforeDeletingTheUser = repo.findById(id).isPresent() ;
			
			repo.deleteById(id);
			
			boolean AfterDeletingTheUser = repo.findById(id).isPresent();
			
			assert(beforeDeletingTheUser)==true;
			
			assert(AfterDeletingTheUser)==false;	
			
		}
}
