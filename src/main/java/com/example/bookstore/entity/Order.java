package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderID;
    java.time.LocalDate LocalDate;
    private long orderPrice;
    private long quantity;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private UserData userData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    @JsonIgnore
    private BookData bookData;

    boolean cancel;
}
