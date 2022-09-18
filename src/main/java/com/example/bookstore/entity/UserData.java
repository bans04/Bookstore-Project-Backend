package com.example.bookstore.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

/**
 * here is UserData class & all its fields
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String username;
    private String password;
//    private String token;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId")
    @JsonIgnore
    private Cart cart;

    @OneToMany(mappedBy = "userData")
    List<Order> order;

    @OneToMany(mappedBy = "userData")
    List<WishList> wishLists;

//    public UserData(UserDataDto userDataDto){
//        this.userId = userId;
//        this.firstName = userDataDto.getFirstName();
//        this.lastName = userDataDto.getLastName();
//        this.email = userDataDto.getEmail();
//        this.address = userDataDto.getAddress();
//        this.username = userDataDto.getUsername();
//        this.password = userDataDto.getPassword();
//    }
}
