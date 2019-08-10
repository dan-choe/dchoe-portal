package com.dchoe.portal.controller;

import com.dchoe.portal.model.Users;
import com.dchoe.portal.repositories.UserRepository;
import com.dchoe.portal.services.CustomUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserService userService;

    @GetMapping("/")
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<List<Users>>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Users getUserBy_id(@PathVariable("id") String id) {
        return userRepository.findBy_id(id);
    }

    @PostMapping()
    public ResponseEntity<String> addUser(@RequestBody Users newUsers) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date currentDate = new Date();
        newUsers.setUpdatedAt(dateFormat.format(currentDate));
        userService.saveUser(newUsers);
        return new ResponseEntity<String>("Added Response", HttpStatus.OK);
    }

    @PutMapping()
    public void modifyUserBy_id(@PathVariable("id") String id, @RequestBody Users users) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date currentDate = new Date();
        users.setUpdatedAt(dateFormat.format(currentDate));
        userRepository.save(users);
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
