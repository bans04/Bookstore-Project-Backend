package com.example.bookstore.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class BookData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    private String bookName;
    private String authorName;
    private long price;
    private long bookPages;
    private long publishedYear;
    private String description;
    private long quantity;

//    @ManyToOne()
//    @JoinColumn(name = "cartId")
//    private Cart cart;

}
