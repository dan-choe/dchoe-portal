package com.dchoe.portal.repositories;

import com.dchoe.portal.model.NodeNav;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeNavRepository extends MongoRepository<NodeNav, String> {
    NodeNav findByName(String name);
}