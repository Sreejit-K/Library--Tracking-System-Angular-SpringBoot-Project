package com.beehyv.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.beehyv.demo.model.Book;
import com.beehyv.demo.repository.BookRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class BookTests {

	
	@Autowired
	private BookRepository repo;
	
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateBook() {
		Book book= new Book( );
		
		book.setSubject("FantasyBooks");
		book.setAuthors("JK_Rawling");
		book.setTitle("HarryPotter3");
		book.setPublisher("ComiconLimited");
		book.setIsbn(12);
		book.setEditionNo(34);
		book.setShelfNo(9);
		book.setNoOfCopies(56);
		book.setNoOfStuBorrowedThisBook(89);
		book.setBookStatus("OutOfStock");
		book.setLibraryName("BeehyvLtd");
		
		Book  bookSaved= repo.save(book);
	
	   assert(bookSaved) != null;
}
	
	@Test
	@Order(2)
	public void testFindBookBytitle() {
		String title = "HarryPotter3";
		Book  book= repo.findByTitle(title);
		
		assertThat(book.getTitle()).isEqualTo(title);
	}
	
	
	@Test
	@Order(3)
	public void testFindBookbySubject() {
		String subject = "FantasyBooks";
		Book  book= repo.findBySubject(subject);
		
		assertThat(book.getSubject()).isEqualTo(subject);
	}
	
	
	@Test
	@Order(4)
	public void testFindBookbysubjectNotExists() {
		String subject = "HorrorStories";
		Book book= repo.findBySubject(subject);
		
		assert (book)==null;
	}
	


	@Test
	@Order(5)
	public void testFindAuthors() {
		String author = "JK_Rawling";
		Book book= repo.findByAuthors(author);
		
		assertThat(book.getAuthors()).isEqualTo(author);
	}
	
	
	@Test
	@Order(6)
	public void testFindBookbyAuthorNotExists() {
		String author = "MatDamen";
		Book book= repo.findByTitle(author);
		
		assert (book)==null;
	}
	
	
	
	
	@Test
	@Order(7)
	public void testFindBookbyTitleNotExists() {
		String Title = "bahibali";
		Book  book= repo.findByTitle(Title);
		
		assert (book)==null;
	}
	
	
	

	@Test
	@Order(8)
	public void testFindBookbyISBN() {
		Integer isbn = 12;
		Book  book= repo.findByIsbn(isbn);
		
		assertThat(book.getIsbn()).isEqualTo(isbn);
	}
	
	
	@Test
	@Order(9)
	public void testFindBookbyISBNNotExists() {
		Integer isbn = 123432;
		Book  book= repo.findByIsbn(isbn);
		
		assert (book)==null;
	}
	
	
	@Test
	@Order(10)
	public void testFindBookbyTitleAndAuthors() {
		String title = "HarryPotter3";
		String author = "JK_Rawling";
		Book  book= repo.findByTitleAndAuthors(title, author);
		
		assertThat(book.getAuthors()).isEqualTo( author);
		assertThat(book.getTitle()).isEqualTo(title);	
	}
	
	@Test
	@Rollback(false)
	@Order(11)
	public void testUpdateBook() {
		String title = "Harry Potter And The Half-Blood prince";
		String authors= "JK_Rawling";
		Book book = repo.findByAuthors(authors);
		
		book.setTitle(title);
		
		
		repo.save(book);
		
		Book  UpdatedBook= repo.findByTitle(title) ;
		
		assertThat(UpdatedBook.getTitle()).isEqualTo(title);
		
		
	}
	
	
	@Test
	@Order(12)
	public void testListBooks() {
		
		List<Book> books = (List<Book>) repo.findAll();
		
		for(Book  book: books) {
			System.out.println(book);
		}
		assertThat(books).size().isGreaterThan(0);
	}
	
	
	@Test
	@Rollback(false)
	@Order(13)
	public void testDeleteBook() {
		String title= "Harry Potter And The Half-Blood prince" ;
		int id = repo.findByTitle(title).getBookId();
		boolean beforeDeletingTheUser = repo.findById(id).isPresent() ;
		
		repo.deleteById(id);
		
		boolean AfterDeletingTheUser = repo.findById(id).isPresent();
		
		assert(beforeDeletingTheUser)==true;
		
		assert(AfterDeletingTheUser)==false;	
		
	}
}
