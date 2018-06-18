package com.JavaRunner.JavaRunner.utils.exceptions;

public class CpfException extends Exception {
    @Override
    public String getMessage() {
        return "CPF doesn't contains the correctly pattern or valid value to CPF algorithm";
    }
}
