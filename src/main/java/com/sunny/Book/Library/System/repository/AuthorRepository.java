package com.sunny.Book.Library.System.repository;

import com.sunny.Book.Library.System.model.Author;
import com.sunny.Book.Library.System.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findById(long id);
    Author findByName(String name);

}
