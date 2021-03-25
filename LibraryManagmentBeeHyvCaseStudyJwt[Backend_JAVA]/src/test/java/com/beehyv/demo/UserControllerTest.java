package com.beehyv.demo;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Assertions;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.beehyv.demo.controller.UserController;

import com.beehyv.demo.model.User;

import com.beehyv.demo.repository.UserRepository;



import javassist.NotFoundException;



@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserControllerTest {
	@Autowired
	private UserController controller;
	
	
	
	
	@Autowired
	private UserRepository repo;
	
	

	//---------------------------------------------------------------------------------------------		
	@Test
	@Rollback(false)
	@Order(1)
	public void testgetAllUsers()  {
		

			try {
				List<User> users  = controller.getAllUserForLibrarian();
				
				for(User  user:  users ) {
					System.out.println(user);
				}
				assertThat( users ).size().isGreaterThan(0);
				

			} catch (NotFoundException e) {
				 Assertions.fail("Exception " + e);
		
			}
			
	
	}

		//---------------------------------------------------------------------------------------------			
		@Test
		@Rollback(false)
		@Order(2)
		public void testgetUserById()  {
			

				try {
					Integer Id = 1;
					ResponseEntity<User>  student= controller.findUserById(Id) ;
					
					assertThat(student.getBody().getId()).isEqualTo(1);
					

				} catch (NotFoundException e) {
					 Assertions.fail("Exception " + e);
			
				}
				
		
		}
		
		
//---------------------------------------------------------------------------------------------		
		
		@Test
		@Rollback(false)
		@Order(3)
		public void testAddUser()  {
			
				try {
					User user = new User( "hanuman" , "hanuman K", "hanuman@beehyv.com","H@numan" );
					
		
					
					   User userSaved=  controller.AddNewUser(user);
					
					 
					   assert (userSaved) != null;

				} catch (Exception e) {
					 Assertions.fail("Exception " + e);
			
				}
				
		
		}
		
	   
		//---------------------------------------------------------------------------------------------		
		@Test
		@Rollback(false)
		@Order(5)
		public void testupdateUser()  {
			
				try {
					
				    String name = "hanumankaraka";
				    String email =  "hanuman@beehyv.com";
				    User user = repo.findByEmail(email);
				    user.setName(name); 
				   
					ResponseEntity<User> updatedStudent = controller.updateUser(user);
					
					
					
					assertThat(updatedStudent.getBody().getName()).isEqualTo("hanumankaraka");
					

				} catch (Exception e) {
					 Assertions.fail("Exception " + e);
			
				}
				
		
		}
		
		
		//---------------------------------------------------------------------------------------------	
		@Test
		@Rollback(false)
		@Order(6)
		public void testdeletUser()  {
			
				try {
					String name= "hanumankaraka" ;
					int id = repo.findByName(name).getId();
					String messageOnDeleting = controller.deleteUser(id);
					
					assertThat(messageOnDeleting).isEqualTo("Librarian has been made inactive with id : " + id);
							

				} catch (Exception e) {
					 Assertions.fail("Exception " + e);
			
				}
				
		
		}
		
		//---------------------------------------------------------------------------------------------	

		@Test
		@Rollback(false)
		@Order(7)
		public void testDeleteuser() {
			String name= "hanumankaraka" ;
			int id = repo.findByName(name).getId();
			boolean beforeDeletingTheUser = repo.findById(id).isPresent() ;
			
			repo.deleteById(id);
			
			boolean AfterDeletingTheUser = repo.findById(id).isPresent();
			
			assert(beforeDeletingTheUser)==true;
			
			assert(AfterDeletingTheUser)==false;	
			
		}
}
