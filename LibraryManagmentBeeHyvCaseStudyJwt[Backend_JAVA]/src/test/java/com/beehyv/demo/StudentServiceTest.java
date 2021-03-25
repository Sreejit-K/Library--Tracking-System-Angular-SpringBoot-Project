package com.beehyv.demo;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.beehyv.demo.Dto.BorrowDTO;
import com.beehyv.demo.Dto.ReturnDto;
import com.beehyv.demo.Dto.StudentDto;
import com.beehyv.demo.model.Book;
import com.beehyv.demo.model.Borrow;
import com.beehyv.demo.model.Penality;
import com.beehyv.demo.model.Student;
import com.beehyv.demo.repository.BookRepository;
import com.beehyv.demo.repository.BorrowRepository;
import com.beehyv.demo.repository.PenalityRepository;
import com.beehyv.demo.repository.StudentRepository;
import com.beehyv.demo.service.BookService;
import com.beehyv.demo.service.StudentService;

import javassist.NotFoundException;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class StudentServiceTest {

	
	@MockBean
    private StudentRepository studentRepository;
	
	@MockBean
    private BookRepository bookRepository;
	
	@MockBean
    private BorrowRepository borrowRepository;
	
	@MockBean
    private PenalityRepository penalityRepository;

	@Autowired
    @InjectMocks
    private StudentService studentService;
    
	@Autowired
    @InjectMocks
    private BookService bookService;
    
    @Mock
    Set<Book> setbooks = new HashSet<Book>();
    
    @Mock
    Set<Student> setstudent = new HashSet<Student>();
    

    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void getAllStudentTest() throws NotFoundException {
    	
    	Mockito.when(studentRepository.findAll()).thenReturn(Stream.of( new Student( "hanuman" , "hanuman@beehyv.com","H@numan","CSE","1234567789","Active"), new Student( "sreejith" , "sreejith@beehyv.com","$Reejith1903","EE","1234567789","Active")).collect(Collectors.toList())) ;
    	
    	assertEquals(2, studentService.getStudents().size());
    }
  
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void saveStudentTest() throws NotFoundException {
    	
    	Student student =  new Student( "hanuman" , "hanuman@beehyv.com","H@numan","CSE","1234567789","Active");
    	
    	Mockito.when(studentRepository.save(student)).thenReturn(student) ;
    	
    	assertEquals(student, studentService.saveStudent(student));
    }
    
  //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void getStudentByIdTest() throws NotFoundException {
    	
    	int id = 1;
    	
    	Optional<Student> student =  Optional.of(new Student(1, "hanuman" , "hanuman@beehyv.com","H@numan","CSE","1234567789","Active" , null));
    
    	Mockito.when(studentRepository.findById(id)).thenReturn(student);
    	
    	assertEquals(student.get(), studentService.getStudentById(id));
    }
    
  //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void getStudentbyEmailTest() throws NotFoundException {
    	
    	String email = "hanuman@beehyv.com";
    	
    	Student student =  new Student( "hanuman" , "hanuman@beehyv.com","H@numan","CSE","1234567789","Active");
    	
    	Mockito.when(studentRepository.findByEmail(email)).thenReturn(student) ;
    	
    	assertEquals(student, studentService.getStudentByEmail(email));
    }
    
    
  //----------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void getStudentbyNameAndEmailTest() throws NotFoundException {
    	String name = "hanuman";
    	
    	String email = "hanuman@beehyv.com";
    	
    	Student student =  new Student( "hanuman" , "hanuman@beehyv.com","H@numan","CSE","1234567789","Active");
    	
    	Mockito.when(studentRepository.findByNameAndEmail(name,email)).thenReturn(student) ;
    	
    	assertEquals(student, studentService.getStudentByNameAndEmail(name, email));
    }
    
    
    
    
  //----------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void getStudentbyNameTest() throws NotFoundException {
    	
    	String name = "hanuman";
    	
    	Student student =  new Student( "hanuman" , "hanuman@beehyv.com","H@numan","CSE","1234567789","Active");
    	
    	Mockito.when(studentRepository.findByName(name)).thenReturn(student) ;
    	
    	assertEquals(student, studentService.getStudentByName(name));
    }
    
    
    
    
    
  //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void deleteStudentByIdTest() throws Throwable {
    	
    	int id = 1 ;
    	
    	Optional<Student> student =  Optional.of(new Student( "hanuman" , "hanuman@beehyv.com","H@numan","CSE","1234567789","Active" ));
    	
    	Mockito.when(studentRepository.findById(id)).thenReturn(student);
    	
    	assertEquals("student has been made inactive with id : " + id , studentService.deleteStudent(id));
    }
    
    
    
  //----------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void updateStudentByIdTest() throws NotFoundException {
    	
   
    	StudentDto studentDto = new StudentDto( 1 ,"hanuman" , "hanuman@beehyv.com","CSE","1234567789","Active","H@numan") ;
    	Optional<Student> student =  Optional.of(new Student(1, "hanuman" , "hanuman@beehyv.com","H@numan","CSE","1234567789","Active",null ));
    	
    	Mockito.when(studentRepository.findById(studentDto.getStudentId())).thenReturn(student);
    	Mockito.when(studentRepository.save(student.get())).thenReturn(student.get());
    	
    	
    	assertEquals(studentDto.toString() , studentService.updateStudent(studentDto).toString());
    }
    
    
  

    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void borrowBookForAStudentTest() throws NotFoundException {
    	
     Date date =new Date(04-18-2020);
     Date date2 = studentService.addDays(date, 30); 
   
    
        BorrowDTO borrowDto = new BorrowDTO(1, 1, 1,"Twilight", "heamsworth", "hanuman", "1234567789", date ,date2,  null ) ;
    	Optional<Book> book = Optional.of(new Book(1,"FantasyBooks", "heamsworth", "Twilight", "ComiconLimited", 34, 12, 56, 9,"BeehyvLtd", "OutOfStock", setstudent, 19)) ;
    	Optional<Student> student =  Optional.of(new Student(1,"hanuman" , "hanuman@beehyv.com","H@numan","CSE","1234567789","Active",setbooks));
    	Borrow borrow  = new  Borrow(1, 1, 1,"Twilight", "heamsworth", "hanuman", "1234567789", date , date2 , null) ;
        Borrow tempborrow = new Borrow( 1, 1,"Twilight", "heamsworth", "hanuman", "1234567789", date , date2 , null);
    	
    	Mockito.when(bookRepository.findById(borrowDto.getBookId())).thenReturn(book);
    	Mockito.when(studentRepository.findById(borrowDto.getStudentId())).thenReturn(student);
    	Mockito.when(bookRepository.save(book.get())).thenReturn(book.get());
    	Mockito.when(borrowRepository.save(tempborrow)).thenReturn(borrow);
    	Borrow Ans = borrowRepository.save(tempborrow)  ; 
    	Borrow Ansf = studentService.getBookForStudent(borrowDto);
    	assertThat(Ansf).isEqualTo(null);
    	assertEquals(borrow ,Ans);
    	
    }
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void returnBookTest() throws NotFoundException {
     	
        Date date =new Date(04-18-2020);
        Date date2 = studentService.addDays(date, 30);
        Date date3 = studentService.addDays(date, 1);
    
       ReturnDto returnDto = new ReturnDto(1,1, 1, date3) ;  
    
   	Optional<Borrow> borrow  = Optional.of(new  Borrow(1, 1, 1,"Twilight", "heamsworth", "hanuman", "1234567789", date , date2 , null)) ;
    Optional<Book> book = Optional.of(new Book(1,"FantasyBooks", "heamsworth", "Twilight", "ComiconLimited", 34, 12, 56, 9,"BeehyvLtd", "OutOfStock", setstudent, 19)) ;
	Optional<Student> student =  Optional.of(new Student(1,"hanuman" , "hanuman@beehyv.com","H@numan","CSE","1234567789","Active",setbooks));
	Penality penality = new  Penality(1, 123456, 1, 1,
			1, date, date2, date3, "FantasyBooks","heamsworth",
			"Twilight", "ComiconLimited", 34, 12, 56, "BeehyvLtd","hanuman" , "hanuman@beehyv.com","CSE","1234567789",5, 1 ,"Book has been returned before the dueDate"); 
	
	Mockito.when(borrowRepository.findByBorrowIdAndBookIdAndStudentId(returnDto.getBorrowId(), returnDto.getBookId(), returnDto.getStudentId())).thenReturn(borrow) ;
	Mockito.when(studentRepository.findById(returnDto.getStudentId())).thenReturn(student);
	Mockito.when(bookRepository.findById(returnDto.getBookId())).thenReturn(book);	
	Mockito.when(borrowRepository.save(borrow.get())).thenReturn(borrow.get());
	Mockito.when(bookRepository.save(book.get())).thenReturn(book.get());
	Mockito.when(penalityRepository.save(penality)).thenReturn(penality);
	Penality Ans = penalityRepository.save(penality);  
	Penality Ansf = studentService.returnBook(returnDto);
	assertThat(Ansf).isEqualTo(null);
	assertEquals(penality ,Ans);
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
      
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
      
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
      
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
      
    
    
  //---------------------------------------------------------------------------------------------------------------------------------------------------- 
    
  
    
}
