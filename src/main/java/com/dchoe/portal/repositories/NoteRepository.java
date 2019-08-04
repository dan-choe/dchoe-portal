package com.dchoe.portal.repositories;

import com.dchoe.portal.model.Note;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    Note findBy_id(ObjectId _id);
}

/*
 int page = 2;
 int pageSize = 5;

 Pageable pageable = new PageRequest(page, pageSize);
 Page<State> states = stateRepo.findAll(pageable);

states.getTotalElements()
states.getTotalPages()


*/