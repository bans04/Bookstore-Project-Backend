package com.example.bookstore.service;

import com.example.bookstore.dto.BookDataDto;
import com.example.bookstore.dto.UserDataDto;
import com.example.bookstore.entity.BookData;
import com.example.bookstore.exception.BookNotFound;
import com.example.bookstore.repository.BookDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookDataService {

    @Autowired
    BookDataRepository bookDataRepository;

    @Autowired
    ModelMapper modelMapper;

    public BookDataDto addBookData(BookDataDto bookDataDto){
        BookData bookData = modelMapper.map(bookDataDto, BookData.class);
        bookDataRepository.save(bookData);
        return  bookDataDto;
    }
    public BookDataDto getBookDataById(long bookId){
        BookData bookData = bookDataRepository.findById(bookId).orElseThrow(()-> new BookNotFound("Book not found with this bookId " + bookId));
        BookDataDto bookDataDto = modelMapper.map(bookData, BookDataDto.class);
        return bookDataDto;
    }

    public List<BookDataDto> getBookData(){
        List<BookData> booksData = bookDataRepository.findAll();
        List<BookDataDto> bookDataDtoList = new ArrayList<>();
        for (BookData bookdata: booksData) {
            BookDataDto bookDataDto = modelMapper.map(bookdata,  BookDataDto.class);
            bookDataDtoList.add(bookDataDto);
        }
//        List<BookDataDto> bookDataDto = Collections.singletonList(modelMapper.map(bookData, BookDataDto.class));
        return bookDataDtoList;
    }

    public BookDataDto deleteBookDataById(long bookId){
//        bookDataRepository.deleteById(bookId);
        BookData bookData = bookDataRepository.findById(bookId).get();
        bookDataRepository.delete(bookData);
        BookDataDto bookDataDto = modelMapper.map(bookData, BookDataDto.class);
        return bookDataDto;
    }

    public BookDataDto updateBookById(long bookId, BookDataDto bookDataDto){
        BookData bookData = bookDataRepository.findById(bookId).get();
        bookData.setBookName(bookDataDto.getBookName());
        bookData.setAuthorName(bookDataDto.getAuthorName());
        bookData.setPrice(bookDataDto.getPrice());
        bookData.setBookPages(bookDataDto.getBookPages());
        bookData.setPublishedYear(bookDataDto.getPublishedYear());
        bookData.setDescription(bookDataDto.getDescription());
        bookDataRepository.save(bookData);
        BookDataDto bookDataDto1 = modelMapper.map(bookData, BookDataDto.class);
        return  bookDataDto1;
    }

}
