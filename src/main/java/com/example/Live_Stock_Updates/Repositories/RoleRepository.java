package com.example.Live_Stock_Updates.Repositories;


import com.example.Live_Stock_Updates.Models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role,Integer> {
    Role findByName(String name);
}
