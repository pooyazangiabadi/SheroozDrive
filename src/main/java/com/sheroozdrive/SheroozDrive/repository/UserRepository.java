package com.sheroozdrive.SheroozDrive.repository;

import com.sheroozdrive.SheroozDrive.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    boolean existsById(String id);
    boolean existsByEmail(String email);
}
