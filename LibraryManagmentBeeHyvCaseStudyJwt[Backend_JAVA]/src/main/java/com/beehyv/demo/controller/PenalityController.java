package com.beehyv.demo.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beehyv.demo.Dto.Fine;

import com.beehyv.demo.model.Penality;

import com.beehyv.demo.repository.FineRepository;
import com.beehyv.demo.service.PenalityService;

import javassist.NotFoundException;

@RestController 
@CrossOrigin(origins = "http://localhost:4200")
public class PenalityController {
	
	@Autowired
	private PenalityService penalityService;
	
	//FineRepository
	@Autowired
    private FineRepository fineRepo ;

//<----------------------------------------------------------------------------------------------------------------------------->	
	
    //return's the list of all returned books with penality details  
	@GetMapping("/getAllpenalityRecords")
	public List<Penality> getListOfAllPenalities() throws NotFoundException{	
        
        return penalityService.getListOfAllPenalities() ;
		    
	}
	
//<----------------------------------------------------------------------------------------------------------------------------->	


    //updates fine system  
	@PutMapping("/updateFine")
	public ResponseEntity<Fine> updateFine( @Valid @RequestBody Fine fine) throws NotFoundException {
		Fine updateFine = fineRepo.findBymyvalue(fine.getMyvalue());
		
		updateFine.setFineForFirstDay(fine.getFineForFirstDay());
		updateFine.setFineAfterFirstDay(fine.getFineAfterFirstDay());
		updateFine.setFineAfterFifthDay(fine.getFineAfterFifthDay());
		
		
		return ResponseEntity.ok().body(fineRepo.save(updateFine));
	}
	
//<----------------------------------------------------------------------------------------------------------------------------->	

	//find fine by id
	//this will be used to update fines 
	@GetMapping("/getFine/{id}")
	public ResponseEntity<Fine> findFineById(@PathVariable int id) throws NotFoundException   {
		 Fine fine = fineRepo.findBymyvalue(id);
		 if(fine==null) {
			 throw new NotFoundException("Borrow Log dosen't exist, check with Librarian");
		 }
		 
		return ResponseEntity.ok().body(fine);
	}

//<-------------------------------------------------------------------------------------------------------------------->	
	
}
