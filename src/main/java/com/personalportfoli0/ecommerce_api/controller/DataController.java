package com.personalportfoli0.ecommerce_api.controller;

import com.personalportfoli0.ecommerce_api.model.*;
import com.personalportfoli0.ecommerce_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class DataController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/addSampleData")
    public String addSampleData() {
        // Add User
        user user = new user();
        user.setUsername("john_doe");
        user.setEmail("john@example.com");
        user.setPassword("password123");
        user.setRole("user");
        userRepository.save(user);

        // Add Product
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("A high-performance laptop.");
        product.setPrice(1200.00);
        product.setStock(10);
        product.setCategory("Electronics");
        productRepository.save(product);

        // Add Cart
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        CartItem cartItem = new CartItem();
        cartItem.setProductId(product.getId());
        cartItem.setQuantity(1);
        cart.setItems(Arrays.asList(cartItem));
        cartRepository.save(cart);

        // Add Order
        Order order = new Order();
        order.setUserId(user.getId());
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(product.getId());
        orderItem.setQuantity(1);
        orderItem.setPrice(1200.00);
        order.setItems(Arrays.asList(orderItem));
        order.setTotalAmount(1200.00);
        order.setPaymentStatus("paid");
        orderRepository.save(order);

        return "Sample data added!";
    }
}
