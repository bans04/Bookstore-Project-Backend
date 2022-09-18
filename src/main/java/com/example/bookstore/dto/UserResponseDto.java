package com.example.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
//    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String username;
    private String password;
    private String token;
}
