package com.personalportfoli0.ecommerce_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.personalportfoli0.ecommerce_api.model.Order;
import com.personalportfoli0.ecommerce_api.repository.OrderRepository;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // Create Order from Cart
    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    // Fetch Order Details
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        return orderRepository.findById(orderId)
                .map(order -> ResponseEntity.ok().body(order))
                .orElse(ResponseEntity.notFound().build());
    }

    // Fetch Order History for a User
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable String userId) {
        return orderRepository.findByUserId(userId);
    }

    // Admin View All Orders
    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
