package com.beehyv.demo;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
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

import com.beehyv.demo.model.Penality;

import com.beehyv.demo.repository.PenalityRepository;
import com.beehyv.demo.service.PenalityService;


import javassist.NotFoundException;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PenalityServiceTest {
	
	@MockBean
    private PenalityRepository penalityRepository;

    @InjectMocks
    private PenalityService penalityService;
    
	
	
//---------------------------------------------------------------------------------------------------------------------------	
	
	
	 @Test
	    public void getAllStudentTest() throws NotFoundException {
	    Date date =new Date(04-18-2020);
	    Date date2 =new Date(05-19-2020); 
	    Date date3 =new Date(05-20-2020); 
	    
	    	
	    	Mockito.when(penalityRepository.findAll()).thenReturn(Stream.of( new Penality(1, 12414325, 1, 1, 1, date, date2, date3, "Romance", "Nolen",
	    			"Titanic", "ambani", 1, 2, 3, "Beehyv", "Sreejith",
	    			"sreejit@beehyv.com", "CSE", "1234567890", 234, 34, "Book has been returned") ,new Penality(2, 12614325, 1, 1, 1, date, date2, date3, "Drama", "JK Rawling",
	    			"harryPotter", "Rawling", 1, 2, 3, "Beehyv", "Sreejith",
	    			"sreejit@beehyv.com", "CSE", "1234567890", 431, 43, "Book has been returned")).collect(Collectors.toList())) ;
	    	
	    	assertEquals(2, penalityService.getListOfAllPenalities().size());
	    }

	 
		
 //---------------------------------------------------------------------------------------------------------------------------
}
