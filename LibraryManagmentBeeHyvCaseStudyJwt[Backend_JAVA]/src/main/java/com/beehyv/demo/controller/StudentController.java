package com.beehyv.demo.controller;

import java.util.Arrays;
import java.util.List;


import javax.validation.Valid;

import org.modelmapper.ModelMapper;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beehyv.demo.Dto.BookDto;
import com.beehyv.demo.Dto.BorrowDTO;
import com.beehyv.demo.Dto.PenalityReportDto;
import com.beehyv.demo.Dto.ReturnDto;
import com.beehyv.demo.Dto.StudentDto;
import com.beehyv.demo.model.Borrow;
import com.beehyv.demo.model.Penality;
import com.beehyv.demo.model.Student;
import com.beehyv.demo.repository.PenalityRepository;
import com.beehyv.demo.service.BorrowService;
import com.beehyv.demo.service.StudentService;

import javassist.NotFoundException;

// STUDENT(backend) or MEMBER(frontend) Entity  are both the same 

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired BorrowService borrowService;
	
	@Autowired
	private PenalityRepository penalityRepo ;
	
	private final ModelMapper modelMapper = new ModelMapper();
	
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	@GetMapping("/getStudent")
	public List<StudentDto> getAllStudents() throws NotFoundException { 		
		
        List<Student> tempStudentList = studentService.getStudents() ;
		
		StudentDto[] studentDtos = modelMapper.map(tempStudentList, StudentDto[].class) ;
		return Arrays.asList(studentDtos);	
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	@GetMapping("/getStudents")
	public List<Student> getAllStudentsForLibrarian() throws NotFoundException{
		return studentService.getStudents();
		
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	@GetMapping("/getStudent/{id}")
	public ResponseEntity<StudentDto> findStudentById(@PathVariable int id) throws NotFoundException   {
		 Student student = studentService.getStudentById(id);
		 
		 StudentDto studentDto = modelMapper.map(student, StudentDto.class);
		 
		return ResponseEntity.ok().body(studentDto);
	}
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	@PutMapping("/updateStudent")
	public ResponseEntity<StudentDto> updateStudent( @Valid @RequestBody StudentDto studentDto) throws NotFoundException {
		
		return ResponseEntity.ok().body(studentService.updateStudent(studentDto));
	}
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	@DeleteMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable int id) throws Exception {
		return studentService.deleteStudent(id);
	}
	
	//<----------------------------------------------------------------------------------------------------------------------------->	
	  
	@PostMapping("/addStudent")
	public Student AddStudent(@RequestBody Student student) throws Exception{
		
		// extract the name and email 
		String name = student.getName();
		String email = student.getEmail();
		
		//check if name & email are  null 
		if( name != null && email != null && !"".equals(name) && !"".equals(email)) {
			Student tempstudent = studentService.getStudentByNameAndEmail(name,email);
			
			//check if Student already exists in the repository
			//If yes then throw exception 
			if(tempstudent!=null) {
				throw new Exception("This student aleady Exists");
			}	
		}
		
		//if the student is not in the current repository then add it into the repository 
		return studentService.saveStudent(student);
		
	}
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	@PostMapping("/borrowBook")
	public ResponseEntity<Borrow> getBookForStudent(@Valid @RequestBody BorrowDTO borrowDto) throws NotFoundException {
		return ResponseEntity.ok(studentService.getBookForStudent(borrowDto));
	}
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	@GetMapping("/getAllBorrowedBooks/{id}")
	public List<BookDto> getAllBorrowedByStudnetId( @PathVariable int id){
		return studentService.getAllBorrowedbooks(id);
	}
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	@PostMapping("/returnBook")
	public ResponseEntity<PenalityReportDto> retutnBook(@Valid @RequestBody  ReturnDto returnDto) throws NotFoundException {		
		return  ResponseEntity.ok(modelMapper.map(studentService.returnBook(returnDto), PenalityReportDto.class));
		
	}
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	@GetMapping("/getPenalityByrandomTokenGenrator/{id}")
	public ResponseEntity<PenalityReportDto> getPenalityByrandomTokenGenrator(@PathVariable int id){
		
		Penality tempPenality = penalityRepo.getPenalityByrandomTokenGenrator(id) ;
		
		PenalityReportDto  penalityDto = modelMapper.map(tempPenality , PenalityReportDto.class);
		return ResponseEntity.ok(penalityDto);
	}
	//<----------------------------------------------------------------------------------------------------------------------------->	
	
	
	
	

}