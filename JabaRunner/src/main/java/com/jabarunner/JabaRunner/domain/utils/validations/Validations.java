package com.jabarunner.JabaRunner.domain.utils.validations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

public class Validations {

    public static Boolean isValidCpf(String cpf) throws Exception {
        if (RegexFilters.isValidCpf(cpf)) {
            cpf = cpf.replace("-", "").replace(".", "");
            if (cpf.length() != 11) return false;
            char firstVerify, secondVerify;
            int totalSum, i, r, num, weight;
            try {
                totalSum = 0;
                weight = 10;
                for (i = 0; i < 9; i++) {
                    num = cpf.charAt(i) - 48;
                    totalSum = totalSum + num * weight;
                    weight = weight - 1;
                }
                r = 11 - totalSum % 11;
                if (r == 10 || r == 11) firstVerify = '0';
                else firstVerify = (char) (r + 48);
                totalSum = 0;
                weight = 11;
                for (i = 0; i < 10; i++) {
                    num = cpf.charAt(i) - 48;
                    totalSum = totalSum + num * weight;
                    weight -= 1;
                }
                r = 11 - totalSum % 11;
                if ((r == 10) || (r == 11)) secondVerify = '0';
                else secondVerify = (char) (r + 48);
                return (firstVerify == cpf.charAt(9)) && (secondVerify == cpf.charAt(10));
            } catch (InputMismatchException error) {
                return false;
            }
        }
        return false;
    }

    public static Boolean passwordEquals(String password, String confirm) {
        return password.equals(confirm);
    }

    public static boolean beforeThanToday(String date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date realDate = null;
        try {
            realDate = dateFormat.parse(date);
        } catch (ParseException ignored) {
        }
        assert realDate != null;
        return new Date().before(realDate);
    }

    public static Boolean afterThanToday(String date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date realDate = null;
        try {
            realDate = dateFormat.parse(date);
        } catch (ParseException ignored) {
        }
        assert realDate != null;
        return new Date().after(realDate);
    }
}

