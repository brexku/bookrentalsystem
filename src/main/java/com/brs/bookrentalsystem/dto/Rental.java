package com.brs.bookrentalsystem.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Rental {
    private int rentalId;
    private int bookId;
    private int custId;
    private LocalDate rentedDate;
    private LocalDate returnDate;
    private int rentalDuration;
    private double rentalPrice;
    private double overDueFee;
    private double totalFee;
    private String rentalStatus;
}
