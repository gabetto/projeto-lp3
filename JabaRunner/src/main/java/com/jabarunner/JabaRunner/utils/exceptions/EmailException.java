package com.jabarunner.JabaRunner.utils.exceptions;

public class EmailException extends Exception {
    public EmailException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Error on email. This email already exist";
    }
}
