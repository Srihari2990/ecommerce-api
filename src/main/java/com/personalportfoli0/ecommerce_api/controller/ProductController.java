package com.personalportfoli0.ecommerce_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.personalportfoli0.ecommerce_api.model.Product;
import com.personalportfoli0.ecommerce_api.repository.ProductRepository;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Create Product (Admin only)
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Update Product (Admin only)
    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @RequestBody Product productDetails) {
        return productRepository.findById(productId).map(product -> {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            return ResponseEntity.ok(productRepository.save(product));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Fetch Products (For Everyone)
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Search Products
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    // Delete Product (Admin only)
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        productRepository.deleteById(productId);
        return ResponseEntity.ok().build();
    }
}
