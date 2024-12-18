package com.example.restPractice.rest;

import com.example.restPractice.pojo.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    List<Student> theStudents;
    @PostConstruct
    public void loadData(){
       theStudents = new ArrayList<>();
        theStudents.add(new Student("sami","khan"));
        theStudents.add(new Student("ahmad","naseer"));
        theStudents.add(new Student("bari","arain"));
        theStudents.add(new Student("Furqan","khan"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;

    }
    @GetMapping("/students/{studentId}")
    public Student getTheStudent(@PathVariable int studentId){
        if(studentId>=theStudents.size()|| studentId<0){
            throw new StudentNotFoundException("student id not found" + studentId);
        }
        return theStudents.get(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> ErrorResponse(StudentNotFoundException exc){
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> Error (Exception exc){

        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


}
