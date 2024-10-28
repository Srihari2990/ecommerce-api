package com.personalportfoli0.ecommerce_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.personalportfoli0.ecommerce_api.model.Order;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    // Fetch all orders for a specific user
    List<Order> findByUserId(String userId);
}
