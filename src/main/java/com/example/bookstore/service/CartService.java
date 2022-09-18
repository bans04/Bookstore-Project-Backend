package com.example.bookstore.service;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.UserData;
import com.example.bookstore.exception.BookNotAvailableInLargeQuantity;
import com.example.bookstore.exception.BookNotFound;
import com.example.bookstore.exception.NoAccountFound;
import com.example.bookstore.exception.UserNotFoundException;
import com.example.bookstore.repository.BookDataRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.UserDataRepository;
import com.example.bookstore.utility.TokenUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    BookDataRepository bookDataRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TokenUtility tokenUtility;

    public Cart addToCart(String token,long bookId, long quantity){
        long userId = tokenUtility.decodeToken(token);
        Cart existingCart = userDataRepository.findById(userId).get().getCart();

        if(existingCart != null){

            BookData bookData = bookDataRepository.findById(bookId).get();
            if(bookData.getQuantity() < quantity){
                throw new BookNotAvailableInLargeQuantity("You entered more quantity than available quantity and Available quantity is " + bookData.getQuantity() );
            }
            bookData.setQuantity(bookData.getQuantity() - quantity);
            existingCart.setBookData(bookData);
            existingCart.setQuantity(quantity);
//            existingCart.getBookDataList().add(bookData);
//            existingCart.getBookDataList().add(bookData);
//            Cart cart = modelMapper.map((Object) bookData, (Type) BookData.class);
            return cartRepository.save(existingCart);
        }
        else {
            throw new NoAccountFound("Please open account, No account found with this " + userId);
        }
//        return null;
    }

    public Cart getCartById(long cartId){
        return cartRepository.findById(cartId).get();
    }

    public List<Cart> getAllCart(){
        List<Cart> cartList = cartRepository.findAll();
        return cartList;
    }

    public Cart removeBook(long bookId, String token){
        long userId = tokenUtility.decodeToken(token);
        UserData userData = userDataRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with this userId " + userId));
        Cart cart = userData.getCart();
        if(cart.getBookData().getBookId() == bookId){
            cart.setBookData(null);
            cartRepository.save(cart);
        }
        return cart;
    }

    public Cart getCardByUser(long userId) {
        Optional<UserData> userData = userDataRepository.findById(userId);
        if(userData.isPresent()){
            return userData.get().getCart();
        }else {
            throw new UserNotFoundException("User not present with this userId " + userId);
        }
    }

    public BookData removeBookFromCart(long bookId, long userId) {
        Optional<UserData> userData = userDataRepository.findById(userId);
        if(userData.isPresent()){
            BookData bookData = userData.get().getCart().getBookData();
            if(bookData.getBookId() == bookId){
                userData.get().setCart(null);
                return userData.get().getCart().getBookData();
            }else {
                throw new  BookNotFound("No book found with this bookId " + bookId);
            }
        }else {
            throw new UserNotFoundException("User not found with this userId " + userId);
        }
    }
}
