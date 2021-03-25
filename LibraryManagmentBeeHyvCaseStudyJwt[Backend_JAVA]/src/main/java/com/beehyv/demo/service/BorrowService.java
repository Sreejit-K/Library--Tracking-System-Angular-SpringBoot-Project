package com.beehyv.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.beehyv.demo.model.Borrow;

import com.beehyv.demo.repository.BorrowRepository;


import javassist.NotFoundException;

@Service
public class BorrowService {

	@Autowired
	private BorrowRepository borrowRepo ;
	

	
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	public Borrow saveBorrowedBook(Borrow borrow) {
		return borrowRepo.save(borrow);
	}
	//<----------------------------------------------------------------------------------------------------------------------------->	
		
	public Borrow getBorrowById(int id) {
	 return borrowRepo.findById(id).orElse(null);	
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	public List<Borrow> getListOfAllBorrowed() throws NotFoundException{
		//Gets All the BorrowedBooks 
		//And throws an exception if there are no books initially present in the BookRepository
			List<Borrow> borrows = borrowRepo.findAll();
			
			//exception handling
			// throws an exception if there are no books in the library  
			if (borrows.size() < 1) {
				throw new NotFoundException("There are no log for Borrowed books in the Library");
			}
			
		   return borrows;
		 

	}

	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	public Optional<Borrow> getBorrowByBorrowId(int id) {
		
		return borrowRepo.findById(id);
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->	
	

    	//Gets the book By Id 
		//And throws an exception if it is not found 
		public List<Borrow> getAllBorrowedBookByStudentId(int id) throws NotFoundException {
			List<Borrow> borrows=borrowRepo.SearchBorrowedBooksBySudentId(id);
			//exception handling
			// throws an exception if there are no books in the library  
			if (borrows.size() < 1) {
				throw new NotFoundException("There are no log for Borrowed books in the Library");
			}
			return borrows ;
		}
	
	//<----------------------------------------------------------------------------------------------------------------------------->	
		
	//<----------------------------------------------------------------------------------------------------------------------------->	
		
		public List<Borrow> getListOfAllBorrowedBasedonReturnDate() throws NotFoundException{
			//Gets All the BorrowedBooks 
			//And throws an exception if there are no books initially present in the BookRepository
				List<Borrow> borrows = borrowRepo.SearchBorrowedBooksByreturnDate();
				
				//exception handling
				// throws an exception if there are no books in the library  
				if (borrows.size() < 1) {
					throw new NotFoundException("There are no log for Borrowed books in the Library");
				}
				
			   return borrows;
			 

		}

	//<----------------------------------------------------------------------------------------------------------------------------->	
		
}
