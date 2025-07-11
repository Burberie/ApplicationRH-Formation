package com.mgas.formation.controller;

import com.mgas.formation.exception.UserDateNotValidException;
import com.mgas.formation.model.UserDTO;
import com.mgas.formation.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getId(id);
    }

    @ValidateOnExecution
    @PostMapping(value = "/save")
    public UserDTO saveUser(@Valid @RequestBody UserDTO user) throws MethodArgumentNotValidException, UserDateNotValidException {
        Calendar current = Calendar.getInstance();
        if (user.getSeniority_date() != null) if (user.getSeniority_date().getTime() > current.getTime().getTime()) throw new UserDateNotValidException("User's seniority date cannot exceed current time", current.getTime());
        if (user.getEnd_date() != null) if (user.getEnd_date().getTime() < current.getTime().getTime()) throw new UserDateNotValidException("User's end contract date cannot be below current time", current.getTime());
        return userService.saveUser(user);
    }

    @ValidateOnExecution
    @PostMapping(value = "/save/list")
    public List<UserDTO> saveUsers(@Valid @RequestBody List<UserDTO> users) throws MethodArgumentNotValidException, UserDateNotValidException {
        Calendar current = Calendar.getInstance();
        for (UserDTO user : users) {
            if (user.getSeniority_date() != null) if (user.getSeniority_date().getTime() > current.getTime().getTime()) throw new UserDateNotValidException("User's seniority date cannot exceed current time", current.getTime());
            if (user.getEnd_date() != null) if (user.getEnd_date().getTime() < current.getTime().getTime()) throw new UserDateNotValidException("User's end contract date cannot be below current time", current.getTime());
        }
        return userService.saveUsers(users);
    }

    @ValidateOnExecution
    @PutMapping(value = "/update/{id}")
    public UserDTO updateUser(@Valid @RequestBody UserDTO user, @PathVariable Long id) throws MethodArgumentNotValidException, UserDateNotValidException {
        Calendar current = Calendar.getInstance();
        if (user.getSeniority_date() != null) if (user.getSeniority_date().getTime() > current.getTime().getTime()) throw new UserDateNotValidException("User's seniority date cannot exceed current time", current.getTime());
        if (user.getEnd_date() != null) if (user.getEnd_date().getTime() < current.getTime().getTime()) throw new UserDateNotValidException("User's end contract date cannot be below current time", current.getTime());
        return userService.updateUser(user, id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}