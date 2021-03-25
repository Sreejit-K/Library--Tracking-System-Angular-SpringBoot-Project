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

import com.beehyv.demo.model.User;
import com.beehyv.demo.repository.BookRepository;
import com.beehyv.demo.repository.BorrowRepository;

import com.beehyv.demo.repository.UserRepository;
import com.beehyv.demo.service.BookService;
import com.beehyv.demo.service.StudentService;
import com.beehyv.demo.service.UserService;

import javassist.NotFoundException;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceTest {

	@MockBean
    private UserRepository userRepository;
	
	@MockBean
    private BookRepository bookRepository;
	
	@MockBean
    private BorrowRepository borrowRepository;

    @InjectMocks
    private StudentService studentService;
    
    @InjectMocks
    private BookService bookService;
    
  
    @InjectMocks
    private UserService userService;
    //----------------------------------------------------------------------------------------------------------------------------------------------------
  
    User user = new User( "hanuman" , "hanuman K", "hanuman@beehyv.com","H@numan" );
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    @Test
    public void saveUserTest() throws NotFoundException {
    	
    	User user = new User(1, "hanuman" , "hanuman K", "hanuman@beehyv.com","H@numan", "1234567890","Active" );
    	
    	Mockito.when(userRepository.save(user)).thenReturn(user) ;
    	
    	assertEquals(user, userService.saveUser(user));
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    @Test
    public void getAllUsersTest() throws NotFoundException {
    	
    	Mockito.when(userRepository.findAll()).thenReturn(Stream.of(new User(1, "hanuman" , "hanuman K", "hanuman@beehyv.com","H@numan", "1234567890","Active" ), new User(2, "sreejith" , "sreejith K", "sreejith@beehyv.com","$Reejith", "1234567890","Active" )).collect(Collectors.toList())) ;
    	
    	assertEquals(2, userService.getAllUsers().size());
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
     
    @Test
    public void getUserByEmailTest() throws NotFoundException {
    	
    	String email = "hanuman@beehyv.com";
    	
    	User user = new User(1, "hanuman" , "hanuman K", "hanuman@beehyv.com","H@numan", "1234567890","Active" );
    	
    	Mockito.when(userRepository.findByEmail(email)).thenReturn(user) ;
    	
    	assertEquals(user, userService.fetchUserByEmailId(email));
    }
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void getUserbyEmailTest() throws NotFoundException {
    	
    	String email = "hanuman@beehyv.com";
    	
    	String password = "H@numan";
    	
    	User user = new User(1, "hanuman" , "hanuman K", "hanuman@beehyv.com","H@numan", "1234567890","Active" );
    	
    	Mockito.when(userRepository.findByEmailAndPassword(email, password)).thenReturn(user) ;
    	
    	assertEquals(user, userService.fetchUserByEmailAndPassword(email, password));
    }
    
    
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
      
    @Test
    public void getUserbyUsernameTest() throws NotFoundException {
    	
    	String username = "hanuman";
    	
    	User user = new User(1, "hanuman" , "hanuman K", "hanuman@beehyv.com","H@numan", "1234567890","Active" );
    	
    	Mockito.when(userRepository.findByUsername(username)).thenReturn(user) ;
    	
    	assertEquals(user, userService.getUserByUsername(username));
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    @Test
    public void getUserByIdTest() throws NotFoundException {
    	
    	int id = 1;
    	
    	Optional<User> user =  Optional.of(new User(1, "hanuman" , "hanuman K", "hanuman@beehyv.com","H@numan", "1234567890","Active" ));
    
    	Mockito.when(userRepository.findById(id)).thenReturn(user);
    	
    	assertEquals(user.get(), userService.getUserById(id));
    }
    

    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
      
    @Test
    public void deleteUserByIdTest() throws Throwable {
    	
    	int id = 1 ;
    	
    	Optional<User> user =  Optional.of(new User(1, "hanuman" , "hanuman K", "hanuman@beehyv.com","H@numan", "1234567890","Active" ));
    	
    	Mockito.when(userRepository.findById(id)).thenReturn(user);
    	
    	assertEquals("Librarian has been made inactive with id : " + id , userService.deleteUser(id));
    }
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Test
    public void updateUserByIdTest() throws NotFoundException {
    	
   
    	
    	Optional<User> user =  Optional.of(new User(1, "hanuman" , "hanuman K", "hanuman@beehyv.com","H@numan", "1234567890","Active" ));
    	
    	Mockito.when(userRepository.findById(user.get().getId())).thenReturn(user);
    	Mockito.when(userRepository.save(user.get())).thenReturn(user.get());
    	
    	
    	assertEquals(user.get() , userService.updateUser(user.get()));
    }
    
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------
      
}
