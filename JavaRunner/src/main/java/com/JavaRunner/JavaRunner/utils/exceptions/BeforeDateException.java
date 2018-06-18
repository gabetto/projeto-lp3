package com.JavaRunner.JavaRunner.utils.exceptions;

public class BeforeDateException extends Exception {
    @Override
    public String getMessage() {
        return "The date argument is before than today. Accept only future dates";
    }
}
