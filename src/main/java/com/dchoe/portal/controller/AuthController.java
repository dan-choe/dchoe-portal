package com.dchoe.portal.controller;

import com.dchoe.portal.model.Role;
import com.dchoe.portal.model.Users;
import com.dchoe.portal.repositories.UserRepository;
import com.dchoe.portal.services.CustomUserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomUserService userService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    /**
     * TODO: Response will be updated to return token
     * @param auth
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Auth auth) {
        Users user = userRepository.findByUsername(auth.getUsername());
        if(user == null) throw new BadCredentialsException("Invalid username");

        boolean validate = bCryptPasswordEncoder.matches(auth.getPassword(), user.getPassword());
        if(validate){
            return new ResponseEntity<>(user.get_id(), HttpStatus.OK);
        }
        throw new BadCredentialsException("Invalid password");
    }
}
