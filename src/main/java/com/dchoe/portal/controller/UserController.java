package com.dchoe.portal.controller;

import com.dchoe.portal.model.User;
import com.dchoe.portal.repository.portalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController
{
    @Autowired
    portalRepository portalRepository;
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers()
    {
        //List<User> userList = new ArrayList<User>();
        //userList.add(new User(1,"test","test-last","test@gmail.com"));
        //return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
        return portalRepository.findAll();
    }
    
    @GetMapping("/users")
    public ReponseEntity User getUser(@RequestParam String target)
    {
        return portalRepository.findOne(target);
    }
    
    @PostMapping()
    public ReponseEntity User addUser(@RequestBody User newUser)
    {
        return portalRepository.save(newUser);
    }
    
    @DeleteMapping("/users")
    public ReponseEntity User removeUser(@RequestParam(value = "userid", required = true) Integer userID)
    {
        portalRepository.findById(id)
            .map(x -> {
                portalRepository.deleteById(id);
                return ResponseEntity.ok().build();
            })
            .orElse(ResponseEntity.notFound().build());
        // return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
    }
       
}
