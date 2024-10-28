package com.personalportfoli0.ecommerce_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.personalportfoli0.ecommerce_api.model.Product;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    // Custom query to search for products by name or category
    List<Product> findByNameContainingIgnoreCase(String keyword);

}
