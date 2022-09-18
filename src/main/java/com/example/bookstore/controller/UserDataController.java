package com.example.bookstore.controller;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.dto.UserDataDto;
import com.example.bookstore.dto.UserResponseDto;
import com.example.bookstore.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserDataController {
    @Autowired
    UserDataService userDataService;

    @PostMapping("register")
    public ResponseEntity<ResponseDto> createUserData(@RequestBody UserDataDto userDataDto) throws MessagingException, UnsupportedEncodingException {
        UserResponseDto savedUserDataDto = userDataService.register(userDataDto);
        ResponseDto responseDto = new ResponseDto("Your data added successfully ", savedUserDataDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/user")
    public  ResponseEntity<ResponseDto> getUserById(@RequestParam long userId){
        UserDataDto userDataDto = userDataService.getUserById(userId);
        ResponseDto responseDto = new ResponseDto("Your data retrieve successfully ", userDataDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/users")
    public  ResponseEntity<ResponseDto> getUsers(){
        List<UserDataDto> userDataDto = userDataService.getUsers();
        ResponseDto responseDto = new ResponseDto("Your data retrieve successfully ", userDataDto);
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public  ResponseEntity<ResponseDto> deleteById(@RequestParam long userId){
        UserDataDto userDataDto = userDataService.deleteById(userId);
        ResponseDto responseDto = new ResponseDto("Your data deleted successfully ", userDataDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateData(@RequestParam long userId, @RequestBody UserDataDto userDataDto){
        UserDataDto userDataDto1 = userDataService.updateUserById(userId, userDataDto);
        ResponseDto responseDto = new ResponseDto("Your data updated successfully ", userDataDto1);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
