package com.mgas.formation.controller;

import com.mgas.formation.entity.DBUser;
import com.mgas.formation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping(value = "")
    public List<DBUser> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping(value = "")
    public DBUser saveUser(@RequestBody DBUser user) {
        return userService.saveUser(user);
    }
}