package com.sunny.Book.Library.System.service;

import com.sunny.Book.Library.System.model.Book;
import com.sunny.Book.Library.System.model.Rental;
import com.sunny.Book.Library.System.repository.BookRepository;
import com.sunny.Book.Library.System.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
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

    // logic for year
    public boolean isValidYear(Book book){
        long year = book.getPublicationYear();
        long currentYear = Year.now().getValue();

        return year <= currentYear;
    }

    public boolean createBook(Book book){

        if(!isISBNValid(book) && !isValidYear(book)) {
           return false;
        }
        bookRepository.save(book);
        return true;
    }

    public Book getBookByName(String title) {

        return bookRepository.findByTitle(title).orElse(null);

    }

    public Book getBookById(long bookId) {

        return bookRepository.findById(bookId).orElse(null);

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

    // get all non-rented books
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

//        return "success";

    }

    public void deleteBookById(long bookId){
        bookRepository.deleteById(bookId);

    }


}
