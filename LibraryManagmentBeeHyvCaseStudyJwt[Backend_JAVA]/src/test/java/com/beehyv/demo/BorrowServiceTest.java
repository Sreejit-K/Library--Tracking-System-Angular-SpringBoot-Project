package com.beehyv.demo;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
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

import com.beehyv.demo.model.Borrow;

import com.beehyv.demo.repository.BookRepository;
import com.beehyv.demo.repository.BorrowRepository;
import com.beehyv.demo.repository.StudentRepository;
import com.beehyv.demo.service.BookService;
import com.beehyv.demo.service.BorrowService;
import com.beehyv.demo.service.StudentService;

import javassist.NotFoundException;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BorrowServiceTest {

	
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
    
    @InjectMocks
    private BorrowService borrowService;
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
   
	
    @Test
    public void saveBorrowTest() throws NotFoundException {
    	
        Date date =new Date(04-18-2020);
        Date date2 =new Date(05-19-2020); 
        
    	Borrow borrow  = new  Borrow(1, 1, 1,"Twilight", "heamsworth", "hanuman", "1234567789", date , date2 , null ) ;
    	
    	Mockito.when(borrowRepository.save(borrow)).thenReturn(borrow) ;
    	
    	assertEquals(borrow, borrowService.saveBorrowedBook(borrow));
    }
    
	
	
	//<----------------------------------------------------------------------------------------------------------------------------->	

    
    @Test
    public void getBorrowByIdTest() throws NotFoundException {
    	
    	int id = 1;
    	
    	 Date date =new Date(04-18-2020);
         Date date2 =new Date(05-19-2020); 
    	
    	Optional<Borrow> borrow =  Optional.of(new  Borrow(1, 1, 1,"Twilight", "heamsworth", "hanuman", "1234567789", date , date2 , null ) );
    
    	Mockito.when(borrowRepository.findById(id)).thenReturn(borrow);
    	
    	assertEquals(borrow.get(), borrowService.getBorrowByBorrowId(id).get());
    }
    
  //<----------------------------------------------------------------------------------------------------------------------------->	
    
    
    @Test
    public void getAllBorrowedTest() throws NotFoundException {
    	
   	 Date date =new Date(04-18-2020);
     Date date2 =new Date(05-19-2020); 
	
    	Mockito.when(borrowRepository.findAll()).thenReturn(Stream.of(new  Borrow(1, 1, 1,"Twilight", "heamsworth", "hanuman", "1234567789", date , date2 , null ), new  Borrow(2, 2, 2,"You", "heamsworth", "Sreejith", "1234567789", date , date2 , null )).collect(Collectors.toList())) ;
    	
    	assertEquals(2, borrowService.getListOfAllBorrowed().size());
    }
  

    
    
 //<----------------------------------------------------------------------------------------------------------------------------->	
 
    @Test
    public void getAllBorrowedBookByStudentIdTest() throws NotFoundException {
   
    	int id = 1;
    	
   	 Date date =new Date(04-18-2020);
     Date date2 =new Date(05-19-2020); 
	
    	Mockito.when(borrowRepository.SearchBorrowedBooksBySudentId(id)).thenReturn(Stream.of(new  Borrow(1, 1, 1,"Twilight", "heamsworth", "hanuman", "1234567789", date , date2 , null ), new  Borrow(2, 2, 2,"You", "heamsworth", "Sreejith", "1234567789", date , date2 , null )).collect(Collectors.toList())) ;
    	
    	assertEquals(2,  borrowService.getAllBorrowedBookByStudentId(id).size());
    }
  
    
    
 //<----------------------------------------------------------------------------------------------------------------------------->	
  
    
    @Test
    public void getAllCurrentlyBorrowedTest() throws NotFoundException {
    	
   	 Date date =new Date(04-18-2020);
     Date date2 =new Date(05-19-2020); 
	
    	Mockito.when(borrowRepository.SearchBorrowedBooksByreturnDate()).thenReturn(Stream.of(new  Borrow(1, 1, 1,"Twilight", "heamsworth", "hanuman", "1234567789", date , date2 , null ), new  Borrow(2, 2, 2,"You", "heamsworth", "Sreejith", "1234567789", date , date2 , null )).collect(Collectors.toList())) ;
    	
    	assertEquals(2,  borrowService.getListOfAllBorrowedBasedonReturnDate().size());
    }
  
    
    
  //<----------------------------------------------------------------------------------------------------------------------------->	
  //<----------------------------------------------------------------------------------------------------------------------------->	
  //<----------------------------------------------------------------------------------------------------------------------------->	
  //<----------------------------------------------------------------------------------------------------------------------------->	
    
    
}
