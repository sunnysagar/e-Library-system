package com.sunny.Book.Library.System.controller;

import com.sunny.Book.Library.System.model.Admin;
import com.sunny.Book.Library.System.model.Student;
import com.sunny.Book.Library.System.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // to get all students
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUpStudent(@Valid @RequestBody Student student){
       studentService.registerStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody Student student){
        Student authenticated = studentService.validateTheAdminRegistration(student);
        if(authenticated != null && studentService.authenticateUser(student.getEmail(), student.getPassword()))
        {
            return ResponseEntity.ok("Logged in successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }

    }

}

