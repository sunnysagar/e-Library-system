package com.sunny.Book.Library.System.model;

//import javax.persistence.*;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rentalInfo")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rentalId;

        @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    @Column(nullable = false)
    private String renterName;


    private LocalDate rentalDate;

    private LocalDate returnDate;

    public Rental() {
    }

    public Rental(long rentalId, Book book, String renterName, LocalDate rentalDate, LocalDate returnDate) {
        this.rentalId = rentalId;
        this.book = book;
        this.renterName = renterName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public long getRentalId() {
        return rentalId;
    }

    public void setRentalId(long rentalId) {
        this.rentalId = rentalId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
