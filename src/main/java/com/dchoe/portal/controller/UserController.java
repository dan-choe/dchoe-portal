package com.dchoe.portal.controller;

import com.dchoe.portal.model.User;
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
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers()
    {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1,"test","test-last","test@gmail.com"));
        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
    }
}