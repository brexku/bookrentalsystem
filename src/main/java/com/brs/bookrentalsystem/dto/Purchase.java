package com.brs.bookrentalsystem.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Purchase {

    private long purchaseId;
        private String bookTitle;
        private String isbn;
        private Date purchaseDate;
        private long quantity;
        private long Price;

}
