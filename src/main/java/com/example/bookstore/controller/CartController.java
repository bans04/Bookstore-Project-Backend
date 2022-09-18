package com.example.bookstore.controller;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;


    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addToCart(@RequestParam String token,long bookId, long quantity){
        Cart cart = cartService.addToCart(token, bookId, quantity);
        ResponseDto responseDto = new ResponseDto("User book Added successfully in cart", cart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/remove")
    public  ResponseEntity<ResponseDto> removeBookFromCart(@RequestParam long bookId, long userId){
        BookData bookData = cartService.removeBookFromCart(bookId, userId);
        ResponseDto responseDto = new ResponseDto("book successfully removed from cart of bookId " + bookId, bookData);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<ResponseDto> getCartById(long cartId){
        Cart cart = cartService.getCartById(cartId);
        ResponseDto responseDto = new ResponseDto("Your cart detail is here ", cart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/usercard")
    public ResponseEntity<ResponseDto> getCardByUser(long userId){
        Cart cart = cartService.getCardByUser(userId);
        ResponseDto responseDto = new ResponseDto("Your cart detail is here:", cart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/carts")
    public ResponseEntity<ResponseDto> getCarts(){
        List<Cart> cart = cartService.getAllCart();
        ResponseDto responseDto = new ResponseDto("Your cart detail is here ", cart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
