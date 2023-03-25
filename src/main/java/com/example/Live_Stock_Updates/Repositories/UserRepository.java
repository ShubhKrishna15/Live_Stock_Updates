package com.example.Live_Stock_Updates.Repositories;

import com.example.Live_Stock_Updates.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User,Integer> {

    Optional<User> findOneByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
