package com.example.bookstore.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * here is UserDataDto which we created for data transfer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto {
//    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String username;
    private String password;

}
