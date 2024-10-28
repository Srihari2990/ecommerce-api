package com.personalportfoli0.ecommerce_api.repository;

import com.personalportfoli0.ecommerce_api.model.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<user, String> {
    Optional<user> findByEmail(String email);
}
