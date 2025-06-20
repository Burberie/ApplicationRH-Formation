package com.mgas.formation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalRegisterDetailsException extends RuntimeException {
    public IllegalRegisterDetailsException(String illegals) {
        super("Unauthorized registry for:\n\n" + illegals + "\nAbove fields should be 'null'.");
    }
}