package io.devboxd.boxd_api.domain.util;

import java.util.ArrayList;
import java.util.List;

public class PasswordRegexUtil {
    public static List<String> validate(String passwd) {
        List<String> errors = new ArrayList<>();

        //^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$
        String length = "^{8,}";
        String digits = "^[0-9]";
        String letters = "^[a-zA-Z]";

        if (!passwd.matches(length))
            errors.add("Password length should be 8 or more characters");

        if (!passwd.matches(digits))
            errors.add("Password must have at least one digit");

        if (!passwd.matches(letters))
            errors.add("Password must have at least one letter");

        return errors;
    }
}
