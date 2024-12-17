package com.example.restPractice.rest;

import com.example.restPractice.pojo.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return theStudents.get(studentId);
    }

}
