package com.mgas.formation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s) { super("User '" + s + "' not found."); }
    public UserNotFoundException(Long id) { super("Could not find User with ID = " + id + "."); }
}