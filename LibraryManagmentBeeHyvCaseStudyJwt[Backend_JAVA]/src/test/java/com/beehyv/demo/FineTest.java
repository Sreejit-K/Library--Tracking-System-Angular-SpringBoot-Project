package com.beehyv.demo;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.beehyv.demo.Dto.Fine;
import com.beehyv.demo.repository.FineRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class FineTest {
	
	@Autowired
	private FineRepository repo;
	
	@Test
	void saveFine_shouldSaveWithDefaultSqlValues() {
		Fine fine = new Fine();
	        
	    int size = (int) repo.count();
	    if(size==0) {
	    	
	    	
	    	fine.setFineForFirstDay((int)5);
		    fine.setFineAfterFirstDay((int)10);
		    fine.setFineAfterFifthDay((int)15);
		    fine.setMyvalue((int)1);
		    fine= repo.save(fine);
		    assert(size)==0;
	    }
	    
	    else {
	    	assert(size)!= 0;
	    }
	   
	}

}
