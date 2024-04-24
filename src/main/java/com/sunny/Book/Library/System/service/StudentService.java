package com.sunny.Book.Library.System.service;

import com.sunny.Book.Library.System.expection.DuplicateUserException;
import com.sunny.Book.Library.System.model.Admin;
import com.sunny.Book.Library.System.model.Student;
import com.sunny.Book.Library.System.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void registerStudent(Student student){
        if(studentRepository.findByEmail(student.getEmail()).isPresent()){
            throw new DuplicateUserException("Duplicate User");
        }

        Student newStudent = new Student();
        newStudent.setName(student.getName());
        newStudent.setEmail(student.getEmail());
        newStudent.setPhone(student.getPhone());
        newStudent.setPassword(student.getPassword());

        studentRepository.save(newStudent);
    }

    public boolean authenticateUser(String email, String password) {
        // Retrieve the user from the database using the username
        Optional<Student> user = studentRepository.findByEmail(email);

        // Check if the user exists and if the provided password matches the stored password
        Student correctUser = user.get();
        return correctUser.getPassword().equals(password);
    }

    public Student validateTheAdminRegistration(Student student) {
        return studentRepository.findByEmailAndPassword(student.getEmail(), student.getPassword()).orElse(null);


    }

}
