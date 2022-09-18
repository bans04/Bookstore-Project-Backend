package com.example.bookstore.controller;
import com.example.bookstore.dto.BookDataDto;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.service.BookDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookDataController {
    @Autowired
    BookDataService bookDataService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> createBookData(@RequestBody BookDataDto bookDataDto){
        BookDataDto bookDataDto1 = bookDataService.addBookData(bookDataDto);
        ResponseDto responseDto = new ResponseDto("Your book data added successfully ", bookDataDto1);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/book")
    public ResponseEntity<ResponseDto> getBooksById(@RequestParam long bookId) {
        BookDataDto bookDataDto = bookDataService.getBookDataById(bookId);
        ResponseDto responseDto = new ResponseDto("Your book data retrieve successfully ", bookDataDto);
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<ResponseDto> getBooks() {
        List<BookDataDto> bookDataDto = bookDataService.getBookData();
        ResponseDto responseDto = new ResponseDto("Your book data retrieve successfully ", bookDataDto);
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<ResponseDto> deleteBooksById(@RequestParam long bookId) {
        BookDataDto bookDataDto = bookDataService.deleteBookDataById(bookId);
        ResponseDto responseDto = new ResponseDto("Your book data deleted successfully ", bookDataDto);
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/update")
    public ResponseEntity<ResponseDto> updateBooksById(@RequestParam long bookId, @RequestBody BookDataDto bookDataDto) {
        BookDataDto bookDataDto1 = bookDataService.updateBookById(bookId, bookDataDto);
        ResponseDto responseDto = new ResponseDto("Your book data updated successfully ", bookDataDto1);
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
