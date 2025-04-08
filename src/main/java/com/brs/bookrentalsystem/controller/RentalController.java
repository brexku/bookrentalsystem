package com.brs.bookrentalsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brs.bookrentalsystem.dto.Rental;
import com.brs.bookrentalsystem.service.RentalService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/report")
    public ResponseEntity<List<Rental>> getRentalTransactionReport() {
        List<Rental> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/{rentalId}")
    public ResponseEntity<Rental> getRentalById(@PathVariable("rentalId") int rentalId) {
        Rental rental = rentalService.getRentalById(rentalId);
        return rental != null ? ResponseEntity.ok(rental) : ResponseEntity.notFound().build();
    }

    @PostMapping("/rent/{bookId}")
    public ResponseEntity<String> rentBook(@PathVariable("bookId") int bookId,
            @RequestParam("custId") int custId,
            @RequestParam("rentalDuration") int rentalDuration,
            @RequestParam("rentalPrice") double rentalPrice) {
        try {
            String response = rentalService.rentBook(bookId, custId, rentalDuration, rentalPrice);
            if (response.startsWith("Book with id")) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(400).body(response);
            }
        } catch (Exception e) {
            System.err.println("Error renting book: " + e.getMessage());
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/return/{rentalId}")
    public ResponseEntity<String> returnBook(@PathVariable int rentalId,
            @RequestParam("returnDate") String returnDate) {
        try {
            LocalDate parsedReturnDate = LocalDate.parse(returnDate);
            String response = rentalService.returnBook(rentalId, parsedReturnDate);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{rentalId}")
    public ResponseEntity<String> deleteRental(@PathVariable int rentalId) {
        try {
            String response = rentalService.deleteRental(rentalId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/inactivate-old")
    public ResponseEntity<String> inactivateOldRentals() {
        try {
            rentalService.inactivateOldRentals();
            return ResponseEntity.ok("Old rentals inactivated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}