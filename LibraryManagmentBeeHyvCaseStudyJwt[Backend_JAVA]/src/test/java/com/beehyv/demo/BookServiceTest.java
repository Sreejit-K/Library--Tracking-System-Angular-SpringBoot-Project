package com.beehyv.demo;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.beehyv.demo.Dto.BookDto;

import com.beehyv.demo.model.Book;
import com.beehyv.demo.repository.BookRepository;
import com.beehyv.demo.repository.BorrowRepository;
import com.beehyv.demo.repository.StudentRepository;
import com.beehyv.demo.service.BookService;
import com.beehyv.demo.service.StudentService;

import javassist.NotFoundException;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BookServiceTest {

	
	@MockBean
    private StudentRepository studentRepository;
	
	@MockBean
    private BookRepository bookRepository;
	
	@MockBean
    private BorrowRepository borrowRepository;

    @InjectMocks
    private StudentService studentService;
    
    @InjectMocks
    private BookService bookService;
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
   
    
    @Test
    public void saveBookTest() throws NotFoundException {
    	
    	
    	Book book = new Book(1,"FantasyBooks", "heamsworth", "Twilight", "ComiconLimited", 34, 12, 56, 9,"BeehyvLtd", "OutOfStock", 19) ;
    	Mockito.when(bookRepository.save(book)).thenReturn(book) ;
    	
    	assertEquals(book, bookService.saveBook(book) );
    }
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void getBookbyTitleTest() throws NotFoundException {
    	
    	String title = "Twilight";
    	
    	Book book = new Book(1,"FantasyBooks", "heamsworth", "Twilight", "ComiconLimited", 34, 12, 56, 9,"BeehyvLtd", "OutOfStock", 19) ;
    	
    	Mockito.when(bookRepository.findByTitle(title)).thenReturn(book) ;
    	
    	assertEquals(book, bookService.getBookByTitle(title));
    }
    
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    @Test
    public void getAllBooksTest() throws NotFoundException {
    	
    	Mockito.when(bookRepository.findAll()).thenReturn(Stream.of( new Book(1,"FantasyBooks", "heamsworth", "Twilight", "ComiconLimited", 34, 12, 56, 9,"BeehyvLtd", "OutOfStock", 19), new Book(2,"Fantasy", "amsworth", "You", "ComiconLimited", 43, 21, 65, 19,"BeehyvLtd", "OutOfStock", 29)).collect(Collectors.toList())) ;
    	
    	assertEquals(2, bookService.getAllBooks().size());
    }
  
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void getBookByIdTest() throws NotFoundException {
    	
    	int id = 1;
    	
    	Optional<Book> book =  Optional.of(new Book(1,"FantasyBooks", "heamsworth", "Twilight", "ComiconLimited", 34, 12, 56, 9,"BeehyvLtd", "OutOfStock", 19));
    
    	Mockito.when(bookRepository.findById(id)).thenReturn(book);
    	
    	assertEquals(book.get(), bookService.getBookById(id));
    }
    
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
      
    @Test
    public void updateStudentByIdTest() throws NotFoundException {
    	
        BookDto bookDto = new BookDto(1,  "heamsworth", "FantasyBooks", "Twilight", "ComiconLimited", 34, 12, 56, 9, "BeehyvLtd", "OutOfStock");
    	
        Optional<Book> book =  Optional.of(new Book(1,"FantasyBooks", "heamsworth", "Twilight", "ComiconLimited", 34, 12, 56, 9,"BeehyvLtd", "OutOfStock", 19));
    	
    	Mockito.when(bookRepository.findById(bookDto.getBookId())).thenReturn(book);
    	Mockito.when(bookRepository.save(book.get())).thenReturn(book.get());
    	
    	
    	assertEquals(bookDto.toString() ,bookService.updateBook(book.get()).toString());
    }
    
    
  
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void deleteStudentByIdTest() throws Throwable {
    	
    	int id = 1 ;
    	
    	Optional<Book> book =  Optional.of(new Book(1,"FantasyBooks", "heamsworth", "Twilight", "ComiconLimited", 34, 12, 56, 9,"BeehyvLtd", "OutOfStock", 19));
    	Mockito.when(bookRepository.findById(id)).thenReturn(book);
    	
    	assertEquals("Book has been made unavailable with id : " + id , bookService.deleteBook(id));
    }
    
   
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
      
    @Test
    public void getAlltrendingBooksTest() throws NotFoundException {
    	
    	Mockito.when(bookRepository.findListOfBestBorrowedBooks()).thenReturn(Stream.of( new Book(1,"FantasyBooks", "heamsworth", "Twilight", "ComiconLimited", 34, 12, 56, 9,"BeehyvLtd", "OutOfStock", 19), new Book(2,"Fantasy", "amsworth", "You", "ComiconLimited", 43, 21, 65, 19,"BeehyvLtd", "OutOfStock", 29)).collect(Collectors.toList())) ;
    	
    	assertEquals(2, bookService.OrderOfBestBooks().size());
    }
  
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    @Test
    public void getAllTrendingBooksPerCategoryTest() throws NotFoundException {
    	
    	String subject = "Fantasy";
    	
    	Mockito.when(bookRepository.SearchBestBooksBySubject(subject)).thenReturn(Stream.of( new Book(1,"Fantasy", "heamsworth", "Twilight", "ComiconLimited", 34, 12, 56, 9,"BeehyvLtd", "OutOfStock", 19), new Book(2,"Fantasy", "amsworth", "You", "ComiconLimited", 43, 21, 65, 19,"BeehyvLtd", "OutOfStock", 29)).collect(Collectors.toList())) ;
    	
    	assertEquals(2, bookService.OrderOfBestSubjectBooks(subject).size());
    }
  
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
      

    @Test
    public void getBookByAuthorsTest() throws NotFoundException {
    	
    	String authors = "heamsworth";
    	
    	Book book = new Book(1,"FantasyBooks", "heamsworth", "Twilight", "ComiconLimited", 34, 12, 56, 9,"BeehyvLtd", "OutOfStock", 19) ;
    	
    	Mockito.when(bookRepository.findByAuthors(authors)).thenReturn(book) ;
    	
    	assertEquals(book, bookService.findByAuthors(authors));
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
      
    
    
  //---------------------------------------------------------------------------------------------------------------------------------------------------- 
    
  
}
