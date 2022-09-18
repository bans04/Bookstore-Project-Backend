package com.example.bookstore.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDataDto {
    private String bookName;
    private String authorName;
    private long price;
    private long bookPages;
    private long publishedYear;
    private String description;
    private long quantity;
}
