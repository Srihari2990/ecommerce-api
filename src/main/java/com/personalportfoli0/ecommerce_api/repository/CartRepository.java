package com.personalportfoli0.ecommerce_api.repository;
import com.personalportfoli0.ecommerce_api.model.Cart;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findByUserId(String userId);


}
