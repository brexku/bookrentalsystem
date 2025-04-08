package com.brs.bookrentalsystem.dto;

import java.sql.Date;

import lombok.Data;

@Data

public class Book {

    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private String bookCategory;
    private Date datePublished;
    private String edition;
    //private int quantity;
    private String bookStatus = "available";
}
