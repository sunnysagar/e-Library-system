package com.sunny.Book.Library.System.controller;

import com.sunny.Book.Library.System.expection.BookNotFoundException;
import com.sunny.Book.Library.System.expection.RentalNotFoundException;
import com.sunny.Book.Library.System.expection.RentedOverdueException;
import com.sunny.Book.Library.System.model.Book;
import com.sunny.Book.Library.System.model.Rental;
import com.sunny.Book.Library.System.service.BookService;
import com.sunny.Book.Library.System.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    public final RentalService rentalService;
    public final BookService bookService;


    public RentalController(RentalService rentalService, BookService bookService) {
        this.rentalService = rentalService;
        this.bookService = bookService;
    }



    // get specific rental from DB
    @GetMapping("{rentalId}")
    public ResponseEntity<?> getRentalById(@PathVariable("rentalId") long rentalId){
        Rental rental = rentalService.getRentalById(rentalId);
        if(rental == null){
            throw new RentalNotFoundException("Rental with ID " + rentalId + " not found.");
        }

        return ResponseEntity.ok().body(rental);
    }

    // Read all rental from DB
    @GetMapping
    public ResponseEntity<List<Rental>> getAllRental(){
        List<Rental> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok().body(rentals);
    }


    //creating the rental into DB
    @PostMapping("/rent/{bookId}/{renterName}")
    public ResponseEntity<?> createForRent(@PathVariable("bookId") long bookId, @PathVariable("renterName") String renterName){
        Book book = bookService.getBookById(bookId);

        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found");
        }
        rentalService.rentBook(book, renterName) ;
            return ResponseEntity.status(HttpStatus.CREATED).body("Book rented successfully");
    }

    // updating th rental into DB
    @PutMapping("{rentalId}")
    public ResponseEntity<?> updateRental(@PathVariable("rentalId") long rentalId, @Valid @RequestBody Rental updatedRental){

        if(rentalService.updateRental(rentalId, updatedRental)) {
            return ResponseEntity.ok().body("Rental with Id " + rentalId + " updated successfully.");
        }

        throw new RentedOverdueException("Cannot update overdue rental");
    }

    // returning the book
    @PostMapping("/return/{bookId}")
    public ResponseEntity<?> returnBook(@PathVariable("bookId") long bookId){
        // Retrieve the book from the database based on the provided book ID

        Book book = bookService.getBookById(bookId);

        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found");
        }

        // Mark the book as returned
        rentalService.returnBook(book);

        return ResponseEntity.ok("Book returned successfully");

    }

    // Deleting Rental from DB
    @DeleteMapping("{rentalId}")
    public ResponseEntity<String> deleteBook(@PathVariable("rentalId") long rentalId){
        rentalService.deleteRentalById(rentalId);
        return ResponseEntity.ok("Rental of ID - "+rentalId +" deleted successfully");
    }


}
