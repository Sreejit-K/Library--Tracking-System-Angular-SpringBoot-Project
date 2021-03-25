package com.beehyv.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.beehyv.demo.model.Borrow;


public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

//-----------------------------------------------------------------------------------------------------------
	Optional<Borrow> findByBookIdAndStudentId(int bookId, int studentId);

//-----------------------------------------------------------------------------------------------------------	
     //This will return the all borrowed books by a student using his student id
	@Query("FROM Borrow B WHERE B.studentId=:id ORDER BY B.issueDate DESC")
	List<Borrow> SearchBorrowedBooksBySudentId(Integer id);
//-----------------------------------------------------------------------------------------------------------
	
	//this will return all currently borrowed books which are yet to be returned
	@Query("FROM Borrow B WHERE B.returnDate is null ORDER BY B.issueDate DESC")
	List<Borrow> SearchBorrowedBooksByreturnDate();

	//-----------------------------------------------------------------------------------------------------------

	Optional<Borrow> findByBorrowIdAndBookIdAndStudentId(int borrowId, int bookId, int studentId);
	
}
