package com.example.UserVerificationApp.validation;


public class ValidatorFactory {
    public static Object getValidator(String type) {
        if ("numeric".equalsIgnoreCase(type)) {
            return NumericValidator.getInstance();
        } else if ("alphabetic".equalsIgnoreCase(type)) {
            return EnglishAlphabetsValidator.getInstance();
        }
        return null;
    }
}
