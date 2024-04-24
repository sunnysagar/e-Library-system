package com.sunny.Book.Library.System.service;

import com.sunny.Book.Library.System.expection.*;
import com.sunny.Book.Library.System.model.Book;
import com.sunny.Book.Library.System.model.Rental;
import com.sunny.Book.Library.System.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class RentalService {

    RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Autowired
   private BookService bookService;

// getting rental by ID
    public Rental getRentalById(long rentalId) {
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);
        if (optionalRental.isEmpty()) {
            throw new RentalNotFoundException("Rental with ID " + rentalId + " not found");
        }
        return optionalRental.get();
    }

    // getting all rentals
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    // Renting a book
    public void rentBook(Book book, String renterName) {
        // check if the book is already rented
        if(rentalRepository.existsByBookAndReturnDateIsNull(book)){
            throw new BookAlreadyRentedException("Book " + book.getTitle()+" is already rented");
        }

        // create a new rental record

        Rental rental = new Rental();
        rental.setBook(book);
        rental.setRenterName(renterName);
        rental.setRentalDate(LocalDate.now());
        rentalRepository.save(rental);

    }

    // Update rental information
    public void updateRental(long rentalId, Rental updatedRental){
        // Find the rental by ID
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);
        if(optionalRental.isEmpty()){
            throw new RentalNotFoundException("Rental with ID "+ rentalId +" not found");
        }

        Rental rental = optionalRental.get();

        // check if the rental is overdue
        if(isRentalOverdue(rental)){
            throw new RentedOverdueException("Cannot update overdue rental");
        }


        // check if the associated book exists
        Book book = rental.getBook();

        // check if the associated book exists
        if(book == null){
            throw new BookNotFoundException("Associated book not found");
        }

        // update rental information

        try{
            // parse the provided date string
            LocalDate rentalDate = LocalDate.parse(updatedRental.getRentalDate());

            // If parsing succeeds, the format is valid
            rental.setRenterName(updatedRental.getRenterName());
            rental.setRentalDate(rentalDate);
            rentalRepository.save(rental);

        }catch (DateTimeParseException e){
            // invalid date format
            throw new InvalidDateFormatException("Invalid rental date format");
        }

    }

    // Returning a book
    public void returnBook(Book book){
        // find the active rental record for the book
        Rental rental = rentalRepository.findByBookAndReturnDateIsNull(book);
        if(rental == null){
            throw new BookNotFoundException("Book " + book.getTitle()+" is not currently rented");
        }

        // Mark the book as available by setting the return date
        rental.setReturnDate(LocalDate.now());
        rentalRepository.save(rental);
    }

    // delete by ID
    public void deleteRentalById(long rentalId){
        // Find the rental by ID
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);
        if(optionalRental.isEmpty()){
            throw new RentalNotFoundException("Rental with ID "+ rentalId +" not found");
        }

        Rental rental = optionalRental.get();

        // check if the rental is overdue
        if(isRentalOverdue(rental)){
            throw new RentedOverdueException("Cannot delete overdue rental");
        }

        // delete the rental
        rentalRepository.deleteById(rentalId);
    }

    // Method to check for overdue rentals(e.g. run daily) with annotation @Scheduled(cron =...)
    @Scheduled(cron = "0 0 0 * * *") // automatically it runs at midnight every day
    public String checkForOverdueRentals(){

        LocalDate today = LocalDate.now();
        LocalDate fourteenDaysAgo = today.minusDays(14);
        List<Rental> overdueRentals = rentalRepository.findOverdueRentalsByReturnDateBefore(fourteenDaysAgo);

        String remainder = "";
        for(Rental rental: overdueRentals){
            if(isRentalOverdue(rental)) {
                // action for overdue rental(e.g. Send remainder)
                remainder = ("Rental ID " + rental.getRentalId() +
                        " is overdue. Renter: " + rental.getRenterName());
            }
        }

        return remainder;
    }

    // Check if rental is overdue
    private boolean isRentalOverdue(Rental rental){
        LocalDate today = LocalDate.now();
        LocalDate rentalDate = LocalDate.parse(rental.getRentalDate());

        // check if the rental period exceeds a set time like 14 days

        return ChronoUnit.DAYS.between(rentalDate, today)>14;
    }
}
