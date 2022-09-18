package com.example.bookstore.service;

import com.example.bookstore.entity.BookData;
import com.example.bookstore.entity.UserData;
import com.example.bookstore.entity.WishList;
import com.example.bookstore.exception.BookNotFound;
import com.example.bookstore.exception.UserNotFoundException;
import com.example.bookstore.exception.WishListNotFound;
import com.example.bookstore.repository.BookDataRepository;
import com.example.bookstore.repository.UserDataRepository;
import com.example.bookstore.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    BookDataRepository bookDataRepository;

    public String createWishlist() {
        WishList wishList = new WishList();
        UserData userData = wishList.getUserData();
        userData.getWishLists().add(wishList);
        wishListRepository.save(wishList);
        return "WishList Created successfully";
    }

    public WishList addBookToWishList(long wishListId, long bookId, long userId) {
        BookData bookData = bookDataRepository.findById(bookId).get();
        if (bookData == null) {
            throw new BookNotFound("Book not found with this bookId " + bookId);
        }
//        WishList wishList = new WishList();
        Optional<WishList> wishList = wishListRepository.findById(wishListId);
        if (wishList.isPresent()) {
            wishList.get().setBookData(bookData);
            UserData userData = userDataRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No user found with this userId " + userId));
            wishList.get().setUserData(userData);
            wishListRepository.save(wishList.get());
            return wishList.get();
        } else {
            throw new WishListNotFound("WishList not found with this wishlistId, please create wishlist and then add " + wishListId);
        }
    }

    public WishList removeWishList(long wishListId) {
        Optional<WishList> wishList = wishListRepository.findById(wishListId);
        if (wishList.isPresent()) {
            wishListRepository.delete(wishList.get());
            WishList wishList1 = wishList.get();
            return wishList1;
        } else {
            throw new WishListNotFound("WishList not found, please create new ");
        }
    }

    public WishList getWishList(long wishlistId) {
        Optional<WishList> wishList = wishListRepository.findById(wishlistId);
        if (wishList.isPresent()) {
            WishList wishList1 = wishList.get();
            return wishList1;
        } else {
            throw new WishListNotFound("WishList not found, please create new ");
        }
    }

    public WishList removeBookFromWishList(long bookId, long wishListId) {
        Optional<WishList> wishList = wishListRepository.findById(wishListId);
        BookData bookData = wishList.get().getBookData();
        if (wishList.isPresent() & bookData.getBookId() == bookId) {
            wishList.get().setBookData(null);
            wishListRepository.save(wishList.get());
            return wishList.get();
        } else {
            throw new WishListNotFound("WishList not found with this wishListId " + wishListId);
        }
    }

    public List<WishList> getWishListByUser(long userId) {
        Optional<UserData> userData = userDataRepository.findById(userId);
        if (userData.isPresent()) {
            List<WishList> wishList = userData.get().getWishLists();
            return wishList;
        } else {
            throw new UserNotFoundException("User not found with this userId " + userId);
        }
    }
}
