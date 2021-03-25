package com.beehyv.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.beehyv.demo.model.Penality;
import com.beehyv.demo.repository.PenalityRepository;

import javassist.NotFoundException;

@Service
public class PenalityService {
	
	@Autowired
	private PenalityRepository penalityRepo ;
	
	
	
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
		public List<Penality> getListOfAllPenalities() throws NotFoundException{
			//Gets All the BorrowedBooks 
			//And throws an exception if there are no books initially present in the BookRepository
				List<Penality> penaities = penalityRepo .findAll();
				
				//exception handling
				// throws an exception if there are no books in the library  
				if (penaities.size() < 1) {
					throw new NotFoundException("There are no log for Borrowed books in the Library");
				}
				
			   return penaities;
			 

		}

		//<----------------------------------------------------------------------------------------------------------------------------->	
		
	


}
