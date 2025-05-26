package com.mgas.formation.controller;

import com.mgas.formation.entity.DBUser;
import com.mgas.formation.exception.UserBirthdateNotValidException;
import com.mgas.formation.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
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
        Calendar verifyDate = Calendar.getInstance();
        verifyDate.add(Calendar.YEAR, -18);
        if (user.getBirthdate().getTime() > verifyDate.getTime().getTime()) {
            throw new UserBirthdateNotValidException();
        }
        return userService.saveUser(user);
    }

    @PostMapping(value = "/save/list")
    public List<DBUser> saveUsers(@RequestBody List<DBUser> users) {
        return userService.saveUsers(users);
    }

    @PutMapping(value = "/update/{id}")
    public DBUser updateUser(@RequestBody DBUser user, @PathVariable Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> wrongArgumentExceptions(HttpMessageNotReadableException ex) {
        return new ResponseEntity<String>("(HttpMessageNotReadableException) Wrong argument on some fields.\n\nBe sure 'birthdate' has the Date format 'DD/MM/YYYY'\nand that 'role' is either 'USER', 'MANAGER' or 'ADMIN'.", HttpStatus.BAD_REQUEST);
    }
}