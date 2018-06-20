package com.JavaRunner.JavaRunner.utils.validations;

import java.util.HashMap;

public interface Validator<T> {
    HashMap<String, String> findErrors() throws Exception;

    T beautify();
}
