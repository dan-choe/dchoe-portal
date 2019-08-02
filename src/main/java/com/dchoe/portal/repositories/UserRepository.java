package com.dchoe.portal.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import org.bson.types.ObjectId;
import com.dchoe.portal.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  User findBy_id(ObjectId _id);
}
