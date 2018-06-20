package com.JavaRunner.JavaRunner.utils.validations;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Salt {
    public static final String saltAdmin = BCrypt.gensalt(12);
}
