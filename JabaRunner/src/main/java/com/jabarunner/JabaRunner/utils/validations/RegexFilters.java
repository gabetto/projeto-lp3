package com.jabarunner.JabaRunner.utils.validations;

import java.util.regex.Pattern;

public class RegexFilters {

    private final static String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final static String cepRegex = "^([0-9]){5}(|-)([0-9]){3}$";
    private final static String cpfRegex = "^([0-9]{3}\\.){2}([0-9]{3})-([0-9]{2})$";
    private final static String nameRegex = "^[A-Za-z 'ÁÉÍÓÚáéíóúÀÈÌÒÙàèìòùÃẼĨÕŨãẽĩõũ]+$";
    private final static String httpUrlRegex = "^http(|s)://[a-zA-z0-9_\\-@/.]+$";

    public static Boolean isValidEmail(String email){
        return Pattern.compile(emailRegex).matcher(email).find();
    }

    public static Boolean isValidCep(String cep){
        return Pattern.compile(cepRegex).matcher(cep).find();
    }

    public static Boolean isValidCpf(String cpf){
        return Pattern.compile(cpfRegex).matcher(cpf).find();
    }

    public static Boolean isValidName(String name){
        return Pattern.compile(nameRegex).matcher(name).find();
    }

    public static Boolean isValidHttpUrl(String url){
        return Pattern.compile(httpUrlRegex).matcher(url).find();
    }
}
