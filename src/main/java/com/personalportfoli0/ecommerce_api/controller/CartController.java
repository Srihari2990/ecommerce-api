package com.personalportfoli0.ecommerce_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.personalportfoli0.ecommerce_api.model.Cart;
import com.personalportfoli0.ecommerce_api.model.CartItem;
import com.personalportfoli0.ecommerce_api.repository.CartRepository;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    // Add Product to Cart
    @PostMapping("/add")
    public Cart addToCart(@RequestParam String userId, @RequestBody CartItem cartItem) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        Cart cart = optionalCart.orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return newCart;
        });
        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }

    // View Cart Items
    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable String userId) {
        return cartRepository.findByUserId(userId).orElse(new Cart());
    }
}
