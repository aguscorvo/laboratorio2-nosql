package com.nosql.laboratorio.dao;

import com.nosql.laboratorio.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository
        extends MongoRepository<User, String> {
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByEmailAndPassword(String email, String password);

}
