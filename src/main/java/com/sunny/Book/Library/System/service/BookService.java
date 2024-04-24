package com.sunny.Book.Library.System.service;

import com.sunny.Book.Library.System.expection.BookNotFoundException;
import com.sunny.Book.Library.System.model.Book;
import com.sunny.Book.Library.System.model.Rental;
import com.sunny.Book.Library.System.repository.BookRepository;
import com.sunny.Book.Library.System.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    RentalRepository rentalRepository;

    // logic for wrong ISBN pattern
    public boolean isISBNValid(Book book){
        String isbn = book.getIsbn();
        String isbnPattern = "^\\d{3}-\\d{10}$";

        return isbn.matches(isbnPattern);
    }

    // creating new book
    public boolean createBook(Book book){

        if(!isISBNValid(book)) {
           return false;
        }
        bookRepository.save(book);
        return true;
    }

    // get specific book by name
    public List<Book> getBookByName(String title) {

        return bookRepository.findByTitleIgnoreCaseContaining(title);
    }

    // get specific book by id
    public Book getBookById(long bookId) {

        return bookRepository.findById(bookId).
                orElseThrow(() ->
                        new BookNotFoundException("Book with ID "+ bookId + " not found."));

    }

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    // get all rented books
    public List<String> getRentedBookNames() {
        List<Book> allBooks = bookRepository.findAll();
        List<Book> rentedBooks = rentalRepository.findByReturnDateNull().stream().map(Rental::getBook).toList();
        return rentedBooks.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    // get all non-rented books or available books
    public List<String> getNonRentedBookNames() {
        List<Book> allBooks = bookRepository.findAll();
        List<Book> rentedBooks = rentalRepository.findByReturnDateNull().stream().map(Rental::getBook).toList();
        List<Book> nonRentedBooks = allBooks.stream().filter(book -> !rentedBooks.contains(book)).toList();
        return nonRentedBooks.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    public boolean updateBook(long bookId, Book updatedBook){
        // check book is present or not
        Optional<Book> bookPresent = bookRepository.findById(bookId);

        if(bookPresent.isPresent()){
            // get the existing book
            Book existingBook = bookPresent.get();

            // Now update the existing book
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthorName(updatedBook.getAuthorName());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setPublicationYear(updatedBook.getPublicationYear());

            // save the updated one
            bookRepository.save(existingBook);

            return true;
        }
        else
            return false;
    }

    // deleting the book, when it is present in DB
    public void deleteBookById(long bookId){
        Optional<Book> bookPresent = bookRepository.findById(bookId);
        if(bookPresent.isEmpty()){
            throw new BookNotFoundException("Book with ID " +bookId + "not present.");
        }

        bookRepository.deleteById(bookId);
    }


}
