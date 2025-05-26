package com.mgas.formation.exception;

public class UserBirthdateNotValidException extends Exception {
    public UserBirthdateNotValidException() {
        super("User must be over 18 years old.");
    }
}