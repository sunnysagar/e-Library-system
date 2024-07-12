package com.sunny.Book.Library.System.repository;


import com.sunny.Book.Library.System.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByEmailAndPassword(String email, String password);
}
