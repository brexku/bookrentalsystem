package com.brs.bookrentalsystem.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brs.bookrentalsystem.dto.Book;
import com.brs.bookrentalsystem.dto.Customer;
import com.brs.bookrentalsystem.dto.Rental;
import com.brs.bookrentalsystem.mapper.BookMapper;
import com.brs.bookrentalsystem.mapper.CustomerMapper;
import com.brs.bookrentalsystem.mapper.RentalMapper;

@Service
public class RentalService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RentalMapper rentalMapper;

    public String rentBook(int bookId, int custId, int rentalDuration, double rentalPrice) {
        Book book = bookMapper.getBookById(bookId);
        if (book == null) {
            return "Book has id " + bookId + " is not found";
        }
        if ("rented".equalsIgnoreCase(book.getBookStatus())) {
            return "Book has id " + bookId + " is already rented!";
        }

        Rental latestRental = rentalMapper.getLatestRentalByBookId(bookId);
        if (latestRental != null && !"returned".equalsIgnoreCase(latestRental.getRentalStatus())) {
            return "Book has id " + bookId + " cannot be rented because it has an "
                    + latestRental.getRentalStatus() + " rental status.";
        }

        Customer customer = customerMapper.getCustomerById(custId);
        if (customer == null) {
            return "Customer has id number " + custId + " not found! Please register the customer before renting.";
        }

        List<Rental> customerRentals = rentalMapper.getRentalsByCustomerId(custId);
        for (Rental rental : customerRentals) {
            if ("active".equalsIgnoreCase(rental.getRentalStatus())) {
                return "Customer has an id number " + custId + " has an active rental (book id " + rental.getBookId()
                        + "). Please return it before renting another.";
            }
        }

        LocalDate rentedDate = LocalDate.now();
        LocalDate returnDate = rentedDate.plusDays(rentalDuration);

        Rental rental = new Rental();
        rental.setBookId(bookId);
        rental.setCustId(custId);
        rental.setRentedDate(rentedDate);
        rental.setReturnDate(returnDate); // Expected return date
        rental.setRentalDuration(rentalDuration); // Initial duration
        rental.setRentalPrice(rentalPrice);
        rental.setOverDueFee(0.0);
        rental.setTotalFee(rentalPrice * rentalDuration);
        rental.setRentalStatus("active");

        rentalMapper.rentBook(rental);
        rentalMapper.markBookAsRented(bookId);

        return "Book with id " + bookId + " rented for customer has id number " + custId + ". Rental ID is: "
                + rental.getRentalId() + ". Total price for " + rentalDuration + " day is "
                + (rentalPrice * rentalDuration) + " Birr. Please return by: " + returnDate;
    }

    public String returnBook(int rentalId, LocalDate returnDate) {
        Rental rental = rentalMapper.getRentalById(rentalId);
        if (rental == null) {
            return "Error: Rental ID " + rentalId + " is not found!";
        }
        if ("returned".equalsIgnoreCase(rental.getRentalStatus())) {
            return "Error: Book has rental ID " + rentalId + " has already been returned!";
        }

        LocalDate expectedReturnDate = rental.getReturnDate();
        long actualDuration = ChronoUnit.DAYS.between(rental.getRentedDate(), returnDate);
        long overdueDays = ChronoUnit.DAYS.between(expectedReturnDate, returnDate);
        overdueDays = Math.max(0, overdueDays);
        double rentalPrice = rental.getRentalPrice();
        double overdueFee = overdueDays * rentalPrice;
        int rentalDuration = rental.getRentalDuration();

        double totalFee = (rentalPrice * rentalDuration) + overdueFee;

        rental.setReturnDate(returnDate);
        rental.setOverDueFee(overdueFee);
        rental.setTotalFee(totalFee);
        rental.setRentalStatus("returned");
        rental.setRentalDuration((int) actualDuration);

        rentalMapper.updateRental(rental);
        rentalMapper.markBookAsReturned(rental.getBookId());

        if (overdueDays == 0) {
            return "Book with id " + rental.getBookId() + " returned on " + rental.getReturnDate() + ". ";
        } else {
            return "Book with id " + rental.getBookId() + ", returned on " + returnDate
                    + ". Days overdue is " + overdueDays
                    + " days, and overdue fee is " + overdueFee + " Birr. Total fee is " + totalFee + " Birr.";
        }
    }

    public List<Rental> getAllRentals() {
        return rentalMapper.getAllRentals();
    }

    public Rental getRentalById(int rentalId) {
        return rentalMapper.getRentalById(rentalId);
    }

    public List<Rental> getRentalReport() {
        return rentalMapper.getAllRentals();
    }

    public String deleteRental(int rentalId) {
        Rental rental = rentalMapper.getRentalById(rentalId);
        if (rental == null) {
            return "Error: Rental ID " + rentalId + " not found!";
        }
        if (!"inactive".equalsIgnoreCase(rental.getRentalStatus())) {
            return "Error: Rental ID " + rentalId + " cannot be deleted because its status is "
                    + rental.getRentalStatus() + ".";
        }
        rentalMapper.deleteRentalById(rentalId);
        return "Rental ID " + rentalId + " deleted successfully.";
    }

    public void inactivateOldRentals() {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        rentalMapper.inactivateOldRentals(oneYearAgo);
    }
}