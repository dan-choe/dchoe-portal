package com.dchoe.portal.controller;

import com.dchoe.portal.model.User;
import com.dchoe.portal.repositories.PortalRepository;
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
public class UserController
{
    @Autowired
    private PortalRepository portalRepository;

    public ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<List<User>>(portalRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id)
    {
        return portalRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<String> addUser(@Valid @RequestBody User newUser)
    {
        portalRepository.save(newUser);
        return new ResponseEntity<String>("Added Response", HttpStatus.OK);
    }

    //    public ResponseEntity<String> removeUser(@RequestParam(value = "userid", required = true) Integer userID)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUser(@PathVariable("id") Long id)
    {
        return portalRepository.findById(id)
                .map(x -> {
                    portalRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
