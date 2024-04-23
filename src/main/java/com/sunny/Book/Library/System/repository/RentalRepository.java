package com.sunny.Book.Library.System.repository;

import com.sunny.Book.Library.System.model.Book;
import com.sunny.Book.Library.System.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Optional<Rental> findByRentalId(long rentalId);
    boolean existsByBookAndReturnDateIsNull(Book book);
    Rental findByBookAndReturnDateIsNull(Book book);
    List<Rental> findOverdueRentalsByReturnDateBefore(LocalDate cutoffDate);
    List<Rental> findByReturnDateNull();

}
