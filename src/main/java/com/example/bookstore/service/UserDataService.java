package com.example.bookstore.service;
import com.example.bookstore.dto.UserDataDto;
import com.example.bookstore.dto.UserResponseDto;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.UserData;
import com.example.bookstore.exception.UserAllreadyExist;
import com.example.bookstore.exception.UserNotFoundException;
import com.example.bookstore.repository.UserDataRepository;
import com.example.bookstore.utility.TokenManager;
import com.example.bookstore.utility.TokenUtility;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDataService {
    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    EmailService emailService;

    @Autowired
    TokenUtility tokenUtility;

//    public UserDataDto addUser(UserDataDto userDataDto) {
//        UserData userData = new UserData(userDataDto);
////        UserData userData = modelMapper.map(userDataDto, UserData.class);
//        userDataRepository.save(userData);
//        return userDataDto;
//    }

    /**
     * purpose:-> This is for new user resister, after successful registration send user detail or verification link on enal
     * @param userDataDto
     * @return
     */
    public UserResponseDto register(UserDataDto userDataDto) {
        UserData existUser = userDataRepository.findByUsername(userDataDto.getUsername());
        if(existUser != null) {
            throw new UserAllreadyExist("User is already exist with this userId " + userDataDto.getUsername() + " Please login !!");
        } else {
            UserData userData = modelMapper.map(userDataDto, UserData.class);
            Cart cart = new Cart();
            cart.setUserData(userData);
            userData.setCart(cart);
            userDataRepository.save(userData);
            String token = tokenUtility.createToken(userData.getUserId());
            UserResponseDto userResponseDto = modelMapper.map(userDataDto, UserResponseDto.class);
            userResponseDto.setToken(token);
            emailService.sendSimpleMail(userData);
            return userResponseDto;
        }
    }

    /**
     * purpose:-> finding user by his userId
     * @param userId
     * @return
     */
    public UserDataDto getUserById(long userId) {
        Optional<UserData> userData = Optional.ofNullable(userDataRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(" No User found with userId " + userId)));
        UserDataDto userDataDto = modelMapper.map(userData.get(), UserDataDto.class);
        return userDataDto;
    }

    /**
     * purpose:-> it will returns all users inside the database
     * @return
     */
    public List<UserDataDto> getUsers() {
        List<UserData> usersData = userDataRepository.findAll();
        List<UserDataDto> userDataList = new ArrayList<>();
        for (UserData userData: usersData) {
            UserDataDto userDataDto = modelMapper.map(userData,  UserDataDto.class);
            userDataList.add(userDataDto);
        }
//        UserDataDto userDataDto = modelMapper.map(usersData,  UserDataDto.class);
        return userDataList;
    }

    /**
     * purpose:-> it will update user details inside the database,by his userId
     * @param userId
     * @param userDataDto
     * @return
     */
    public UserDataDto updateUserById(long userId, UserDataDto userDataDto){
        Optional<UserData> userData = Optional.ofNullable(userDataRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(" No User found with userId " + userId)));
        userData.get().setFirstName(userDataDto.getFirstName());
        userData.get().setLastName(userDataDto.getLastName());
        userData.get().setEmail(userDataDto.getEmail());
        userData.get().setAddress(userDataDto.getAddress());
        userData.get().setUsername(userDataDto.getUsername());
        userData.get().setPassword(userDataDto.getPassword());
        userDataRepository.save(userData.get());
        UserDataDto userDataDto1 = modelMapper.map(userData.get(), UserDataDto.class);
        return  userDataDto1;
    }

    /**
     * purpose:-> it will delete user inside the database by his userId
     * @param userId
     * @return
     */
    public UserDataDto deleteById(long userId){
        UserData userData = userDataRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(" No User found with userId " + userId));
        userDataRepository.delete(userData);
        UserDataDto userDataDto = modelMapper.map(userData, UserDataDto.class);
        return userDataDto;
    }
}
