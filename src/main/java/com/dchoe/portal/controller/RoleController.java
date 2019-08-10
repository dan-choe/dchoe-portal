package com.dchoe.portal.controller;

import com.dchoe.portal.model.Role;
import com.dchoe.portal.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<List<Role>>(roleRepository.findAll(), HttpStatus.OK);
    }

}
