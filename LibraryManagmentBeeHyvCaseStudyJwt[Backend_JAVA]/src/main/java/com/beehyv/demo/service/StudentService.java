package com.beehyv.demo.service;

import java.sql.Date;
import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beehyv.demo.Dto.BookDto;
import com.beehyv.demo.Dto.BorrowDTO;
import com.beehyv.demo.Dto.Fine;
import com.beehyv.demo.Dto.ReturnDto;
import com.beehyv.demo.Dto.StudentDto;
import com.beehyv.demo.model.Book;
import com.beehyv.demo.model.Borrow;
import com.beehyv.demo.model.Penality;
import com.beehyv.demo.model.Student;
import com.beehyv.demo.repository.BookRepository;
import com.beehyv.demo.repository.BorrowRepository;
import com.beehyv.demo.repository.FineRepository;
import com.beehyv.demo.repository.PenalityRepository;
import com.beehyv.demo.repository.StudentRepository;

import javassist.NotFoundException;


@Service
public class StudentService {
	
	// upperBound for RandomToken generator
	public final int  upperbound = 2147483646;
	
	//Time period for due date 
	//Student has to return the book before 30 days of borrow
	public final int DAYS_AFTER_BORROWED = 30;
	
	private final ModelMapper modelMapper = new ModelMapper();

	//<----------------------------------------------------------------------------------------------------------------------------->
	
	
	//Book Service 
//	@Autowired
//	private BookService bookservice;
			
	//StudentRepository
	@Autowired
	private StudentRepository studentRepo;
	
	//BookRepository
	@Autowired
	private BookRepository bookRepo;
	
	//BorrowRepository
	@Autowired
	private BorrowRepository borrowRepo;
	
	//PenalityRepository
	@Autowired
	private PenalityRepository penalityRepo ;


	//FineRepository
		@Autowired
		private FineRepository fineRepo ;
	//<----------------------------------------------------------------------------------------------------------------------------->
	
	public StudentService(StudentRepository studentRepo, BookRepository bookRepo, BorrowRepository borrowRepo) {
		super();
		this.studentRepo = studentRepo;
		this.bookRepo = bookRepo;
		this.borrowRepo = borrowRepo;
	}

	//<----------------------------------------------------------------------------------------------------------------------------->
	
	//Initially I have considered this application for students, so made functions based on students. Don't get confused between Students/Members both are the same
	// Save a Student/Member Functionality
	public Student saveStudent(Student student) {
		return studentRepo.save(student);
	}


	//<----------------------------------------------------------------------------------------------------------------------------->
	
	//get List of students in the repository  
	//And throws an exception if there are no books initially present in the BookRepository
	public List<Student> getStudents() throws NotFoundException {
		      
		        List<Student> students = studentRepo.findAll();
		        
		        //exception handling
				// throws an exception if there are no books in the library  
				if (students.size() < 1) {
					throw new NotFoundException("There are no books in the Library");
				}
				
				 return students;
				
	}


	//<----------------------------------------------------------------------------------------------------------------------------->
	

	//Gets the Student By Id 
	//And throws an exception if it is not found 
	public Student getStudentById(int id) throws NotFoundException {
		Student student = studentRepo.findById(id).orElse(null);
		
		//exception handling
		// throws an exception if book is not found
		if(student==null) {
			throw new NotFoundException("Student does not exist id : " + id);
		}
		return student ;
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->
	
	//Gets the Student By Name 
	//And throws an exception if it is not found 
	public Student getStudentByName(String username) throws NotFoundException {
		Student student = studentRepo.findByName(username);
		

		//exception handling
		// throws an exception if book is not found
		if(student==null) {
			throw new NotFoundException("Student does not exist username : " + username);
		}
		
		return student;
	}

	
	//<----------------------------------------------------------------------------------------------------------------------------->
	

	public Student getStudentByEmail(String email) {
		return studentRepo.findByEmail(email);
	}

	//<----------------------------------------------------------------------------------------------------------------------------->
	

	public Student getStudentByNameAndEmail(String name, String email) {
		Student existingStudent = studentRepo.findByNameAndEmail(name,email);
		return existingStudent;
	}

	//<----------------------------------------------------------------------------------------------------------------------------->
	

	//Deleting a Student/Member 
	public String deleteStudent(int id) throws Exception {
		Student student=  studentRepo.findById(id).orElse(null);
		
		//exception handling
		// throws an exception if Student/Member is not found
		if(student==null) {
			throw new NotFoundException("Student does not exist id : " + id);
		}
		else if(student.getBooks().size()!= 0) {
			for(Book book : student.getBooks()){
				   Optional<Borrow> borrow =  borrowRepo.findByBookIdAndStudentId(book.getBookId(), student.getStudentId());
				   if(borrow.get().getReturnDate()== null) {
					   throw new Exception("member with id: "+ id +" has currently borrowed Books, you cannot make the member incative unless he/she retruns all the books" );
				   }
				  
				}
			
			
			//check if Student/Member status is already Inactive 
	    	if(student.getStatus() =="InActive") {
	    		
	    	    //if yes return exception 
	    		throw new Exception("Book Status is already made Unavilable");
	    	}
	    	
	    	//else make it Inactive 
	    	//set Student status to Inactive when deleted 
			student.setStatus("InActive");
			
			//now update this to the Repository 
			studentRepo.save(student);
			
			//now return the message 
			return "student has been made inactive with id : " + id;
		}
		else {
			
				//check if Student/Member status is already Inactive 
		    	if(student.getStatus() =="InActive") {
		    		
		    	    //if yes return exception 
		    		throw new Exception("Book Status is already made Unavilable");
		    	}
		    	
		    	//else make it Inactive 
		    	//set Student status to Inactive when deleted 
				student.setStatus("InActive");
				
				//now update this to the Repository 
				studentRepo.save(student);
				
				//now return the message 
				return "student has been made inactive with id : " + id;
		
		}
	}

	//<----------------------------------------------------------------------------------------------------------------------------->
	
	//Updating the Student Functionality 
	public StudentDto updateStudent( StudentDto studentDto) throws NotFoundException {
		//get the Student and store it in a local variable
		Student existingStudent = studentRepo.findById(studentDto.getStudentId()).orElse(null);
		
		 //exception handling
	     // throws an exception if student is not found
		if(existingStudent==null) {
			throw new NotFoundException("Student does not exist id : " +studentDto.getStudentId());
		}
		
		//now set values that we receive from the REST services, to this local variables 
		existingStudent.setName(studentDto.getName());
		existingStudent.setPassword(studentDto.getPassword());
		existingStudent.setEmail(studentDto.getEmail() );
		existingStudent.setDepartment(studentDto.getDepartment());
		existingStudent.setPhone(studentDto.getPhone());
		existingStudent.setStatus(studentDto.getStatus());
		
		//update the book using this local variable
		final Student updatedStudent = studentRepo.save(existingStudent);
		
		// convert the local variable to StudentDto using ModelMapping and return it 
		return modelMapper.map(updatedStudent, StudentDto.class);
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->
	
	
	//Borrowing a Book for a student Functionality  
	public Borrow getBookForStudent(@Valid BorrowDTO borrowDto ) throws NotFoundException{
		
		//get the Book and store it in a local variable
		Optional<Book> bookChecked  = bookRepo.findById(borrowDto.getBookId());
		
		//exception handling
	    // throws an exception if book is not found
		if(!bookChecked.isPresent()) {
			throw new NotFoundException("Book dosen't exist");
		}
		
		//get the Student and store it in a local variable
		Optional<Student> tempStudent  = studentRepo.findById(borrowDto.getStudentId());
		//exception handling
	    // throws an exception if student is not found
		if(!tempStudent.isPresent()) {
			throw new NotFoundException("Student dosen't exist");
		}
		
		
		//issue date
		Date issueDate = borrowDto.getIssueDate(); 
		
		//Use a tempBorrow entity to store the object/log to the borrowAndReturnTable
		Borrow tempBorrow = new Borrow();
		
		//Store the book data, student data along with issue date in the borrow table/Repository 
		tempBorrow.setBookId(bookChecked.get().getBookId());
		tempBorrow.setStudentId(tempStudent.get().getStudentId());
		tempBorrow.setIssueDate(issueDate);
		tempBorrow.setBookAuthors(bookChecked.get().getAuthors());
		tempBorrow.setBookName(bookChecked.get().getTitle());
		tempBorrow.setStudentName(tempStudent.get().getName());
		tempBorrow.setStudentPhone(tempStudent.get().getPhone());
		
		//use the addDays function to set the due date in the borrow repository
		tempBorrow.setDueDate(this.addDays(issueDate, DAYS_AFTER_BORROWED));
		
		
		//Map this book data in the studentTable for further use(which shows the List of books which have been borrowed by this student)
		//this will add this book in the list of borrowed books set in a student entity
		tempStudent.get().addNewBook(bookChecked.get());
		
		
		//Map this student data in the bookTable for further use(which shows the List of students who have borrowed this books)
		//this will add the student details who borrowed this book in the book entity
		bookChecked.get().addStudent(tempStudent.get());
		
		//now count the no of students who borrowed this book by checking the size of the above List of students  
		bookChecked.get().setNoOfStuBorrowedThisBook(bookChecked.get().getStudentsWhoBooked().size());
		
		
		// This will decrease the no of copies in the repository as the book as been borrowed  
		int noOfCopies = bookChecked.get().getNoOfCopies();
		noOfCopies--;
		
		//check if the book is out of stock/unavailable(i.e no of copies=0) when its is borrowed and update it in the repository
		if( noOfCopies == 0) {
			bookChecked.get().setBookStatus("UnAvailable");
		}
		
		//save the no of copies in the temporary book
		bookChecked.get().setNoOfCopies(noOfCopies);
		//save this data of Temporary Book in the bookService 
//		bookservice.saveBook(bookChecked.get());
		
		bookRepo.save(bookChecked.get());
		
		//add this borrow history to borrowAndReturnTable and return this 	
		Borrow borrowlog =  borrowRepo.save(tempBorrow);
  		return borrowlog;
		
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->
	
    //Function to calculate the due date 
	//This is used in the BorrowBook function
	
	//method for adding the max_No_Of_Days after borrowing the book and return it to the returnDate
	public Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    
	//<----------------------------------------------------------------------------------------------------------------------------->
	
    //get's the list of all borrowed books by StudentId from studnetDto
	public List<BookDto> getAllBorrowedbooks(int student_id) {
		Optional<Student> tempStudent= studentRepo.findById(student_id);
		BookDto[] bookDtos = modelMapper.map(tempStudent.get().getBooks(), BookDto[].class) ;	
		return  Arrays.asList(bookDtos);
	}

	
	//<----------------------------------------------------------------------------------------------------------------------------->
	

	public Penality returnBook(@Valid ReturnDto returnDto) throws NotFoundException {
//		Optional<Borrow> tempBorrow = borrowRepo.findByBookIdAndStudentId(returnDto.getBookId(), returnDto.getStudentId()) ;
		Optional<Borrow> tempBorrow = borrowRepo.findByBorrowIdAndBookIdAndStudentId(returnDto.getBorrowId(), returnDto.getBookId(), returnDto.getStudentId()) ;
		//Throw an error if the booking is not recorded
		if(!tempBorrow.isPresent()) {
			throw new NotFoundException("Borrow Log dosen't exist, check with Librarian");
		}
		
		Optional<Student> tempStudent= studentRepo.findById(returnDto.getStudentId());
		Optional<Book> tempBook  = bookRepo.findById(returnDto.getBookId());
		
		
		//get Due Date
		Date dueDate = tempBorrow.get().getDueDate();
		
		//get return Date 
		//This we will get from the UI using Rest calls
		Date returnDate = returnDto.getReturnDate();
		tempBorrow.get().setReturnDate(returnDate);
		
		//save this return date to the existing log 
		borrowRepo.save(tempBorrow.get());
		
		//convert the due date & return date for easy calculation of no of days after the due date 
		LocalDate dueDateL = Date.valueOf(dueDate.toString()).toLocalDate();
		LocalDate returnDateL = Date.valueOf(returnDate.toString()).toLocalDate();
		
		//calculates the no of days of late submission
		int No_of_days = (int) ChronoUnit.DAYS.between(dueDateL,returnDateL);
		
		
		//This is a random Token generator for the Penalty Slip 
		Random rand = new Random();
		int generateRandomNo = rand.nextInt(upperbound);
		
		//save all the data into a penalty entity to get the Fines so that librarian can easily access all the data at once
		Penality tempPenality = new Penality(); 
		
		//inserting borrow details 
		tempPenality.setBorrowId(tempBorrow.get().getBorrowId());
		tempPenality.setRandomTokenGenrator(generateRandomNo);
		tempPenality.setBookId(tempBorrow.get().getBookId());
		tempPenality.setStudentId(tempBorrow.get().getStudentId());
		tempPenality.setIssueDate(tempBorrow.get().getIssueDate());
		tempPenality.setDueDate(tempBorrow.get().getDueDate());
		tempPenality.setReturnDate(tempBorrow.get().getReturnDate());
		
		//Inserting book details that has been borrowed and returned  
		tempPenality.setSubject( tempBook.get().getSubject());
		tempPenality.setTitle( tempBook.get().getTitle());
		tempPenality.setPublisher( tempBook.get().getPublisher());
		tempPenality.setEditionNo( tempBook.get().getEditionNo());
		tempPenality.setIsbn(tempBook.get().getIsbn()); 
		tempPenality.setLibraryName(tempBook.get().getLibraryName()); 
		tempPenality.setShelfNo(tempBook.get().getShelfNo());
		tempPenality.setAuthors(tempBook.get().getAuthors());
		
		//Inserting Student details who borrowed
		tempPenality.setName(tempStudent.get().getName());
		tempPenality.setEmail(tempStudent.get().getEmail());
		tempPenality.setDepartment(tempStudent.get().getDepartment());
		tempPenality.setPhone(tempStudent.get().getPhone());
		
		//insert the penalty details
		tempPenality.setFine(calculateFine(No_of_days));
		tempPenality.setNo_of_days(No_of_days);
		
		//If no of days is negative then booked has been submitted before the dew date 
		//else fine will be issued 
		if(No_of_days <= 0) {
			tempPenality.setMessage("Book has been returned before the dueDate");
			
		}
		else {
			tempPenality.setMessage("Fine for the late submission is:" + calculateFine(No_of_days) );	
		}
				
		//remove this book from the Student entity where the all the borrowed books by a particular student are stored 
		tempBook.get().removeStudent(tempStudent.get());	
		
		//now increase the no of copies because the book has been submitted successfully 
		int noOfCopies = tempBook.get().getNoOfCopies();
		
		//check if no of copies is zero, so that we can make the book available again 
		if(noOfCopies==0) {
			tempBook.get().setBookStatus("Available");
		}
		
		noOfCopies++;
		tempBook.get().setNoOfCopies(noOfCopies);
		
		//save this book data into BookRepository
//		bookservice.saveBook(tempBook.get());
		bookRepo.save(tempBook.get());
		
		//Return the Penality details 
		return penalityRepo.save(tempPenality);
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->
	
   //This Function will calculate the fines 
	public int calculateFine(int NoOfDays) {
		Fine fine = fineRepo.findBymyvalue(1) ;
		
		
		int penality=  0 ;
		
		if(NoOfDays == 1) {
			penality += fine.getFineForFirstDay();
		}
		else if(NoOfDays>=2 && NoOfDays<=5 ) {
			penality+=(NoOfDays-1)* (fine.getFineAfterFirstDay()) ;
		}
		else if(NoOfDays>5) {
			penality+= (NoOfDays-5)*(fine.getFineAfterFifthDay());
		}
		
		return penality;
		
	}
	
	
	//<----------------------------------------------------------------------------------------------------------------------------->
	

}

