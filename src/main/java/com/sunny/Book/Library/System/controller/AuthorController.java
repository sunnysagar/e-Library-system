package com.sunny.Book.Library.System.controller;


import com.sunny.Book.Library.System.model.Author;
import com.sunny.Book.Library.System.model.Book;
import com.sunny.Book.Library.System.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/author")
@Validated
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // read the author by id from DB
    @GetMapping("{authorId}")
    public Author getAuthor(@PathVariable("authorId") long authorId){

        return authorService.getauthor(authorId);
    }

    // read all author from DB
    @GetMapping
    public List<Author> getAllAuthor(){
        return authorService.getAllAuthor();
    }

    // read all book by author name from DB
    @GetMapping("{authorName}/books")
    public List<Book> getAllBooksByAuthor(@PathVariable("authorName") String authorName){
        return authorService.getAllBookByAuthorName(authorName);
    }

    // create the author in DB
    @PostMapping
    public String createAuthor(@Valid @RequestBody Author author){
        authorService.findOrCreateAuthor(author.getName(), author.getBiography());
        return "Author has added successfully";
    }

    // update the author details in DB
    @PutMapping("{authorId}")
    public ResponseEntity<?> updateAuthor(@Valid @PathVariable("authorId") long authorId, @RequestBody Author author){
        if(authorService.updateAuthor(authorId,author)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Author " + authorId + " updated successfully.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Author "+authorId +" is not exist.");
    }

    // deleting author from DB
    @DeleteMapping("{authorId}")
    public String deleteAuthor(@PathVariable("authorId") long authorId){
        authorService.deleteAuthor(authorId);
        return "Deleting author is successfully";
    }
}
