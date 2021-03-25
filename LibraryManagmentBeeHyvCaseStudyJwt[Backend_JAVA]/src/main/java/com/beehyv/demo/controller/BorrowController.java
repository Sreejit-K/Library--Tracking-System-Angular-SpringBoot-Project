package com.beehyv.demo.controller;



import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.beehyv.demo.Dto.BorrowDTO;
import com.beehyv.demo.model.Borrow;
import com.beehyv.demo.service.BorrowService;

import javassist.NotFoundException;

// Borrow service stores and returns the Issue Details 

//<-------------------------------------------------------------------------------------------------------------------->	

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BorrowController {

	@Autowired
	private BorrowService borrowService;
	
	private final ModelMapper modelMapper = new ModelMapper();


//<----------------------------------------------------------------------------------------------------------------------------->	
	
	
	
	        //return's the list of allBorrowedBooks in the Repository 
			@GetMapping("/getAllBorrowedbooks")
			public List<BorrowDTO> getAllBorrowedBooks() throws NotFoundException{	
		        
		        List<Borrow> tempBorrowList =  borrowService.getListOfAllBorrowed() ;
				
			     BorrowDTO[] borrowDtos = modelMapper.map(tempBorrowList, BorrowDTO[].class) ;
				return Arrays.asList(borrowDtos);	
				    
			}
			
//<----------------------------------------------------------------------------------------------------------------------------->	
	
			
	        //return's the list of allBorrowedBooks in the Repository 
			@GetMapping("/getAllBorrwedByStudentId/{id}")
			public List<BorrowDTO> getAllBorrowedBooksByStudentId(@PathVariable int id) throws NotFoundException{	
		        
		        List<Borrow> tempBorrowList =  borrowService.getAllBorrowedBookByStudentId(id) ;
				
			     BorrowDTO[] borrowDtos = modelMapper.map(tempBorrowList, BorrowDTO[].class) ;
				return Arrays.asList(borrowDtos);	
				    
			}
			
//<----------------------------------------------------------------------------------------------------------------------------->	
//<----------------------------------------------------------------------------------------------------------------------------->	
			
	        //return's the list of allBorrowedBooks in the Repository 
			@GetMapping("/getOnlyBorrowedbooks")
			public List<BorrowDTO> getOnlyBorrowedBooks() throws NotFoundException{	
		        
		        List<Borrow> tempBorrowList =  borrowService.getListOfAllBorrowedBasedonReturnDate() ;
				
			     BorrowDTO[] borrowDtos = modelMapper.map(tempBorrowList, BorrowDTO[].class) ;
				return Arrays.asList(borrowDtos);	
				    
			}
			
//<----------------------------------------------------------------------------------------------------------------------------->	
				
			
}
