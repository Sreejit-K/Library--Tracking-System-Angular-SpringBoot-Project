package com.beehyv.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.beehyv.demo.model.User;
import com.beehyv.demo.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class UserTest {

	
	@Autowired
	private UserRepository repo;
	
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateUser() {
		User user= new User( "hanuman" , "hanuman K", "hanuman@beehyv.com","H@numan" );
	    User userSaved= repo.save(user);
	
	   assert (userSaved) != null;
}
	
	@Test
	@Order(2)
	public void testFindUserbyName() {
		String name = "hanuman K";
		User user= repo.findByName(name);
		
		assertThat(user.getName()).isEqualTo(name);
	}
	
	@Test
	@Order(3)
	public void testFinduserbyNameNotExists() {
		String name = "ekalavya";
		User user= repo.findByName(name);
		
		assert (user)==null;
	}
	
	@Test
	@Order(4)
	public void testFindUserbyUsername() {
		String username = "hanuman";
		User user= repo.findByUsername(username);
		
		assertThat(user.getUsername()).isEqualTo(username);
	}
	
	
	@Test
	@Order(5)
	public void testFindUserbyEmail() {
		String email= "hanuman@beehyv.com";
		User user= repo.findByEmail(email);
		
		assertThat(user.getEmail()).isEqualTo(email);
	}
	
	
	@Test
	@Rollback(false)
	@Order(6)
	public void testUpdateUser() {
		String name = "hanumankaraka";
		String username= "hanuman";
		User user = repo.findByUsername(username);
		
		 user.setName(name);
		
		
		repo.save(user);
		
		User Updateduser= repo.findByName(name) ;
		
		assertThat(Updateduser.getName()).isEqualTo(name);
		
		
	}
	
	
	@Test
	@Order(7)
	public void testListUsers() {
		
		List<User> users = (List<User>) repo.findAll();
		
		for(User user: users) {
			System.out.println(user);
		}
		assertThat(users).size().isGreaterThan(0);
	}
	
	
	@Test
	@Rollback(false)
	@Order(8)
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