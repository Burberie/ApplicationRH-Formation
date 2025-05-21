package com.mgas.formation.controller;

import com.mgas.formation.entity.DBUser;
import com.mgas.formation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
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

    @GetMapping(value = "/{id}")
    public DBUser getUser(@PathVariable Long id) {
        return userService.getId(id);
    }

    @PostMapping(value = "")
    public DBUser saveUser(@RequestBody DBUser user) {
        return userService.saveUser(user);
    }

    @PutMapping(value = "/{id}")
    public DBUser replaceUser(@RequestBody DBUser user, @PathVariable Long id) {
        return userService.replaceUser(user, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}