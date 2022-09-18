    package com.example.bookstore.service;

    import com.example.bookstore.entity.BookData;
    import com.example.bookstore.entity.Cart;
    import com.example.bookstore.entity.Order;
    import com.example.bookstore.entity.UserData;
    import com.example.bookstore.exception.InvalidOrderId;
    import com.example.bookstore.exception.UserNotFoundException;
    import com.example.bookstore.repository.BookDataRepository;
    import com.example.bookstore.repository.CartRepository;
    import com.example.bookstore.repository.OrderRepository;
    import com.example.bookstore.repository.UserDataRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.time.LocalDate;
    import java.util.List;
    import java.util.Optional;


    @Service
    public class OrderService {

        @Autowired
        UserDataRepository userDataRepository;

        @Autowired
        OrderRepository orderRepository;

        @Autowired
        BookDataRepository bookDataRepository;

        @Autowired
        CartRepository cartRepository;

        public Order createOrder(long userId, String address) {
            UserData userData = userDataRepository.findById(userId).get();
            if(userData != null){
                Cart cart = userData.getCart();
                BookData bookData = cart.getBookData();
                long totalOrderAmount = cart.getQuantity() * cart.getBookData().getPrice();
                Order order = new Order();
                order.setOrderPrice(totalOrderAmount);
                order.setQuantity(cart.getQuantity());
                order.setAddress(address);
                order.setUserData(userData);
                order.setCancel(false);
                order.setLocalDate(LocalDate.now());
                order.setBookData(bookData);
                userData.getOrder().add(order);
                userDataRepository.save(userData);
                orderRepository.save(order);
//                Order order1 = orderRepository.findById(order.getOrderID()).get();
//                cart.setQuantity(cart.getQuantity() - order1.getQuantity());
//                cart.setBookData(null);
                cart.setBookData(null);
                cart.setQuantity(null);
                cartRepository.save(cart);
                return order;
            }else {
                throw new UserNotFoundException("User not found with this userId" + userId);
            }
        }

        public Order cancelOrder(long orderId){
            Optional<Order> order = orderRepository.findById(orderId);
            if(order.isPresent()){
//                orderRepository.delete(order.get());
                order.get().setCancel(true);
                orderRepository.save(order.get());

                long cancelQuantity = order.get().getQuantity();
                BookData bookData = order.get().getBookData();
                bookData.setQuantity(bookData.getQuantity() + cancelQuantity);
                bookDataRepository.save(bookData);
                return order.get();
            }else{
                throw new InvalidOrderId("No order present with this orderId" + orderId);
            }
//            return order.get();
        }

        public Order getOrderById(long orderId) {
            Optional<Order> order = orderRepository.findById(orderId);
            if(order.isPresent()){
                return orderRepository.findById(orderId).get();
            }else{
                throw new InvalidOrderId("No order present with this orderId" + orderId);
            }
        }

        public List<Order> getOrderByUser(long userId) {
            Optional<UserData> userData = userDataRepository.findById(userId);
            if(userData.isPresent()){
                 List<Order> order = userData.get().getOrder();
                 return order;
            }else{
                throw new InvalidOrderId("User not found with this userId " + userId);
            }
        }
    }
