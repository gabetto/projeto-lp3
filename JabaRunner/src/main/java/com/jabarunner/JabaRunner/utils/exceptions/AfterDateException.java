package com.jabarunner.JabaRunner.utils.exceptions;

public class AfterDateException extends Exception {
    @Override
    public String getMessage() {
        return "The date argument is after than today. Accept only past dates";
    }
}
