package com.dchoe.portal.repositories;

import com.dchoe.portal.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {
    Users findBy_id(String _id);
    Users findByUsername(String username);
}
