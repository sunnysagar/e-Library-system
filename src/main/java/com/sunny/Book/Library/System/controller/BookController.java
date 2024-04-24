package com.sunny.Book.Library.System.controller;


import com.sunny.Book.Library.System.expection.BookNotFoundException;
import com.sunny.Book.Library.System.expection.CustomExceptionHandler;
import com.sunny.Book.Library.System.expection.InvalidFormatException;
import com.sunny.Book.Library.System.model.Author;
import com.sunny.Book.Library.System.model.Book;
import com.sunny.Book.Library.System.service.AuthorService;
import com.sunny.Book.Library.System.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public int bookCount;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    // Read specific book by id from DB
    @GetMapping("{title}")
    public ResponseEntity<?> getBookByTitle(@Valid @PathVariable("title") String  title){
        List<Book> book = bookService.getBookByName(title);
        if(book == null){
            throw new BookNotFoundException(title + " book not found");
        }

        return ResponseEntity.ok(book);
    }

    //Read All book from DB
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBook();
    }

    // Get all rented books
    @GetMapping("/rented")
    public ResponseEntity<List<String>> getRentedBook(){
        List<String> rentedBookNames = bookService.getRentedBookNames();
        return ResponseEntity.ok(rentedBookNames);
    }

    //Get All non-rented books
    @GetMapping("/available")
    public ResponseEntity<List<String>> getNonRentedBookNames() {
        List<String> nonRentedBookNames = bookService.getNonRentedBookNames();
        return ResponseEntity.ok(nonRentedBookNames);
    }


    // Adding the book into the DB
    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book){

        String authorName = book.getAuthorName();
        // save the author if not exist
        Author author = authorService.saveThroughBook(authorName);

        // create the book
        if(bookService.createBook(book)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully.");
        }
        throw new InvalidFormatException("Error! Once check the each parameter format.");

    }


    // updating the book into the DB
    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable("bookId") long bookId, @Valid @RequestBody Book book)
    {
        if(bookService.updateBook(bookId,book)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Book "+ bookId +" updated successfully.");

        }
        throw new BookNotFoundException("Book with ID " + bookId + " not found.");

    }

    // Deleting book from DB
    @DeleteMapping("{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") long bookId){
        bookService.deleteBookById(bookId);
        return ResponseEntity.ok("Book of ID - "+bookId +" deleted successfully");
    }


}

