package com.JavaRunner.JavaRunner.utils.validations;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Salt {
    public static String saltAdmin;

    static {
        try {
            saltAdmin = BCrypt.gensalt(13, SecureRandom.getInstanceStrong());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
