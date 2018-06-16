package com.jabarunner.JabaRunner.domain.utils.contracts;

public interface ModelValidation<T> {
    T validate() throws Exception;
}
