package com.brs.bookrentalsystem.dto;

import lombok.Data;

@Data
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String userPhone;
    private String userName;
    private String password;
    private String role;
}
