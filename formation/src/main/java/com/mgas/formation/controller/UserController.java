package com.mgas.formation.controller;

import com.mgas.formation.entity.DBUser;
import com.mgas.formation.exception.UserDateNotValidException;
import com.mgas.formation.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @ValidateOnExecution
    @PostMapping(value = "/save")
    public DBUser saveUser(@Valid @RequestBody DBUser user) throws Exception {
        Calendar current = Calendar.getInstance();
        user.setRegister_date(current.getTime());
        if (user.getSeniority_date().getTime() > current.getTime().getTime()) { throw new UserDateNotValidException("User's seniority date cannot exceed current time", current.getTime()); }
        if (user.getEnd_date() != null) { if (user.getEnd_date().getTime() < current.getTime().getTime()) { throw new UserDateNotValidException("User's end contract date cannot be below current time", current.getTime()); } }
        return userService.saveUser(user);
    }

    @ValidateOnExecution
    @PostMapping(value = "/save/list")
    public List<DBUser> saveUsers(@Valid @RequestBody List<DBUser> users) throws Exception {
        Calendar current = Calendar.getInstance();
        for (DBUser user : users) {
            user.setRegister_date(current.getTime());
            if (user.getSeniority_date().getTime() > current.getTime().getTime()) { throw new UserDateNotValidException("User's seniority date cannot exceed current time", current.getTime()); }
            if (user.getEnd_date() != null) { if (user.getEnd_date().getTime() < current.getTime().getTime()) { throw new UserDateNotValidException("User's end contract date cannot be below current time", current.getTime()); } }
        }
        return userService.saveUsers(users);
    }

    @ValidateOnExecution
    @PutMapping(value = "/update/{id}")
    public DBUser updateUser(@Valid @RequestBody DBUser user, @PathVariable Long id) throws Exception {
        Calendar current = Calendar.getInstance();
        user.setRegister_date(current.getTime());
        if (user.getSeniority_date().getTime() > current.getTime().getTime()) { throw new UserDateNotValidException("User's seniority date cannot exceed current time", current.getTime()); }
        if (user.getEnd_date() != null) { if (user.getEnd_date().getTime() < current.getTime().getTime()) { throw new UserDateNotValidException("User's end contract date cannot be below current time", current.getTime()); } }
        return userService.updateUser(user, id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}