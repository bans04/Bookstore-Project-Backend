package com.example.bookstore.controller;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.entity.WishList;
import com.example.bookstore.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> addToWishList(){
        String wishList = wishListService.createWishlist();
        ResponseDto responseDto = new ResponseDto("Book added successfully in wishList", wishList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/addbook")
    public ResponseEntity<ResponseDto> addBookToWishList(@RequestParam long wishListId, long bookId, long userId){
        WishList wishList = wishListService.addBookToWishList(wishListId, bookId, userId);
        ResponseDto responseDto = new ResponseDto("Book added successfully in wishList", wishList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/wishlist")
    public ResponseEntity<ResponseDto> getWishList(@RequestParam long wishlistId){
        WishList wishListResponseDto = wishListService.getWishList(wishlistId);
        ResponseDto responseDto = new ResponseDto("Book added successfully in wishList", wishListResponseDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> removeWishList(@RequestParam long wishListId){
        WishList wishList = wishListService.removeWishList(wishListId);
        ResponseDto responseDto = new ResponseDto("Your wishlist deleted successfully", wishList);
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/removebook")
    public  ResponseEntity<ResponseDto> removeBookFromWishList(@RequestParam long bookId, long wishListId){
        WishList wishList = wishListService.removeBookFromWishList(bookId, wishListId);
        ResponseDto responseDto = new ResponseDto("Book removed successfully from your wishlist of bookId" + bookId,wishList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/userwishlist")
    public ResponseEntity<ResponseDto> getWishListByUser(@RequestParam long userId){
        List<WishList> wishList = wishListService.getWishListByUser(userId);
        ResponseDto responseDto = new ResponseDto("All WishList for UserId " + userId, new ResponseDto());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
