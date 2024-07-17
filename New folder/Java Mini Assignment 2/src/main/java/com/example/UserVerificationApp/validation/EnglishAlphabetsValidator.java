package com.example.UserVerificationApp.validation;


public class EnglishAlphabetsValidator {
    private static EnglishAlphabetsValidator instance;

    private EnglishAlphabetsValidator() {}

    public static EnglishAlphabetsValidator getInstance() {
        if (instance == null) {
            instance = new EnglishAlphabetsValidator();
        }
        return instance;
    }

    public boolean validate(String input) {
        return input.matches("[a-zA-Z]+");
    }
}
