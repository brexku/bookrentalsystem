package com.brs.bookrentalsystem.dto;

import lombok.Data;

@Data
public class Customer {

    private int custId;
    private String custFname;
    private String custMname;
    private String custLname;
    private String custNidNumber;
    private String custPhone;
    private String custEmail;

}
