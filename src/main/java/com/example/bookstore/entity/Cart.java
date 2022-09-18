package com.example.bookstore.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserData userData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    @JsonIgnore
    private BookData bookData;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bookData")
////    @JoinColumn(name = "booklistId")
//    List<BookData> bookDataList;

    private Long quantity;

    public Cart(UserData userData, BookData bookData) {
        this.userData = userData;
        this.bookData = bookData;
    }


//    private float totalPrice;

}
