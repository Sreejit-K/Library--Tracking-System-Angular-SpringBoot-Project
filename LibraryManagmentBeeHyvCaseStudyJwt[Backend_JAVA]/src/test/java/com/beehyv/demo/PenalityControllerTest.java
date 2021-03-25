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

import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.beehyv.demo.Dto.Fine;
import com.beehyv.demo.controller.PenalityController;
import com.beehyv.demo.model.Penality;

import javassist.NotFoundException;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PenalityControllerTest {
	
	

	@Autowired
	private PenalityController controller;
	

//---------------------------------------------------------------------------------------------	
	@Test
	@Rollback(false)
	@Order(1)
	public void testgetListOfAllPenalities()  {
		

			try {
				List<Penality> penalties  = controller.getListOfAllPenalities();
				
				for(Penality  penality: penalties) {
					System.out.println(penality);
				}
				assertThat(penalties).size().isGreaterThan(0);
				

			} catch (NotFoundException e) {
				 Assertions.fail("Exception " + e);
		
			}
			
	
	}
	
//---------------------------------------------------------------------------------------------	
	
	@Test
	@Rollback(false)
	@Order(2)
	public void testUpdateFine()  {

			try {
					
				ResponseEntity<Fine> fine = controller.findFineById(2);
				
				fine.getBody().setFineForFirstDay(5);
				fine.getBody().setFineAfterFirstDay(11);
				fine.getBody().setFineAfterFifthDay(16);
				
				ResponseEntity<Fine> updatedfine  = controller.updateFine(fine.getBody());
				
				assertThat(updatedfine.getBody().getFineAfterFifthDay()).isEqualTo(16);
				assertThat(updatedfine.getBody().getFineAfterFirstDay()).isEqualTo(11);
				assertThat(updatedfine.getBody().getFineForFirstDay()).isEqualTo(5);
				
			} catch (NotFoundException e) {
				 Assertions.fail("Exception " + e);
		
			}	
	
	}
	
//-----------------------------------------------------------------------------------------------------	

}
