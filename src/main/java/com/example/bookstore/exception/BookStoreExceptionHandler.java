package com.example.bookstore.exception;

import com.example.bookstore.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class BookStoreExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List errorList = ex.getBindingResult().getAllErrors().stream().map(objectError ->
                objectError.getDefaultMessage()).collect(Collectors.toList());
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", errorList);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ResponseDto> noSuchElementExceptionHandler(UserNotFoundException ex) {
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserAllreadyExist.class)
    public ResponseEntity<ResponseDto> userAllreadyExistExceptionHandler(UserAllreadyExist ex) {
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(value = NoAccountFound.class)
    public ResponseEntity<ResponseDto> noAccountFound(NoAccountFound ex) {
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BookNotFound.class)
    public ResponseEntity<ResponseDto> bookNotFound(BookNotFound ex) {
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidOrderId.class)
    public ResponseEntity<ResponseDto> invalidOrderId(InvalidOrderId ex) {
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotAvailableInLargeQuantity.class)
    public ResponseEntity<ResponseDto> bookNotAvailableInLargeQuantity(BookNotAvailableInLargeQuantity ex) {
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }

    @ExceptionHandler(WishListNotFound.class)
    public ResponseEntity<ResponseDto> wishListNotFound(WishListNotFound ex) {
        ResponseDto responseDto = new ResponseDto("Exception occurs while processing rest request", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
}
