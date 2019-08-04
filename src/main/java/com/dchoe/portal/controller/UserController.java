package com.dchoe.portal.controller;

import com.dchoe.portal.model.User;
import com.dchoe.portal.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public User getUserBy_id(@PathVariable("id") String id) {
        return userRepository.findBy_id(id);
//        return new ResponseEntity<User>(userRepository.findBy_id(id), HttpStatus.OK);
//        return new ResponseEntity<User>(userRepository.findBy_id(new ObjectId(id)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addUser(@RequestBody User newUser) {
        userRepository.save(newUser);
        return new ResponseEntity<String>("Added Response", HttpStatus.OK);
    }

    @PutMapping()
    public void modifyUserBy_id(@PathVariable("id") String id, @RequestBody User user) {
//        user.set_id(id);
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") String id) {
        userRepository.delete(userRepository.findBy_id(id));
//        return userRepository.findBy_id(id)
//                .map(x -> {
//                    userRepository.deleteById(id);
//                    return ResponseEntity.ok().build();
//                }).orElse(ResponseEntity.notFound().build());
    }
}
