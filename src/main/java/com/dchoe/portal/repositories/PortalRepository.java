package com.dchoe.portal.repositories;

import com.dchoe.portal.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// <type of values, type of id>
@Repository
public interface PortalRepository extends MongoRepository<User, String> {
//  List<User> findByName(@Param("username") String username);
}
