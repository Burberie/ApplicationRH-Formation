package com.mgas.formation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserDateNotValidException extends RuntimeException {
    public UserDateNotValidException(String msg, Date date) {
        super(msg + " (" + date + ").");
    }
}