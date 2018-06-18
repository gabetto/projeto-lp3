package com.jabarunner.JabaRunner.utils.exceptions;

public class PasswordException extends Exception {

    public PasswordException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Error on password, please, confirm your password";
    }
}
