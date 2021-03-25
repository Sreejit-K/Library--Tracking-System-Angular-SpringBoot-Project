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


import com.beehyv.demo.Dto.BorrowDTO;

import com.beehyv.demo.controller.BorrowController;


import javassist.NotFoundException;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BorrowControllerTest {

	@Autowired
	private BorrowController controller;
	
	
	
	
	
	

	//---------------------------------------------------------------------------------------------	
		@Test
		@Rollback(false)
		@Order(1)
		public void testgetAllBorrowedBooks(){
			

				try {
					List<BorrowDTO> borrows  = controller.getAllBorrowedBooks();
					
					for(BorrowDTO borrow:  borrows ) {
						System.out.println(borrow);
					}
					assertThat( borrows).size().isGreaterThan(0);
					

				} catch (NotFoundException e) {
					 Assertions.fail("Exception " + e);
			
				}
				
		
		}

		
			//---------------------------------------------------------------------------------------------	
			@Test
			@Rollback(false)
			@Order(2)
			public void testgetOnlyBorrowedBooks(){
				

					try {
						List<BorrowDTO> borrows  = controller.getOnlyBorrowedBooks();
						
						for(BorrowDTO borrow:  borrows ) {
							System.out.println(borrow);
						}
						assertThat( borrows).size().isGreaterThan(0);
						

					} catch (NotFoundException e) {
						 Assertions.fail("Exception " + e);
				
					}
					
			
			}
			
}