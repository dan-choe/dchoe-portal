package com.dchoe.portal.repositories;

import com.dchoe.portal.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface portalRepository extends MongoRepository<User, String> {

}