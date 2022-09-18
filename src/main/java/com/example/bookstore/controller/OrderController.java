package com.example.bookstore.controller;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.entity.Order;
import com.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestParam long userId, String address){
        Order order = orderService.createOrder(userId, address);
        ResponseDto responseDto = new ResponseDto("Order created successfully", order);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/order")
    public ResponseEntity<ResponseDto> getOrderById(long orderId){
        Order order = orderService.getOrderById(orderId);
        ResponseDto responseDto = new ResponseDto("Your order retrieve successfully ", order);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/userorder/{userId}")
    public ResponseEntity<ResponseDto> getUserOrder(@PathVariable long userId){
        List<Order> order = orderService.getOrderByUser(userId);
        ResponseDto responseDto = new ResponseDto("Your order retrieve successfully", order);
        return new  ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity createOrder(@PathVariable long orderId){
        Order order = orderService.cancelOrder(orderId);
        ResponseDto responseDto = new ResponseDto("Order cancel successfully", order);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }
}
