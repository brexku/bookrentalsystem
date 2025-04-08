package com.brs.bookrentalsystem.dto;

import lombok.Data;

@Data
public class RentalRequest {
    private int bookId;
    private int custId;
    private int rentalDuration;
    private double rentalPrice;
}
