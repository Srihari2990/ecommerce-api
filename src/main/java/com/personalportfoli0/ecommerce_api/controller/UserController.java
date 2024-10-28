package com.personalportfoli0.ecommerce_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.personalportfoli0.ecommerce_api.model.user;
import com.personalportfoli0.ecommerce_api.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Create User (Admin only)
    @PostMapping("/create")
    public user createUser(@RequestBody user user) {
        return userRepository.save(user);
    }

    // Update User (Admin only)
    @PutMapping("/update/{userId}")
    public ResponseEntity<user> updateUser(@PathVariable String userId, @RequestBody user userDetails) {
        return userRepository.findById(userId).map(user -> {
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword()); // ensure this is hashed
            return ResponseEntity.ok(userRepository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete User (Admin only)
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    // Get User Details (User or Admin)
    @GetMapping("/{userId}")
    public ResponseEntity<user> getUserById(@PathVariable String userId) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }
}
