package com.example.UserVerificationApp.validation;

public class NumericValidator {
    private static NumericValidator instance;

    private NumericValidator() {}

    public static NumericValidator getInstance() {
        if (instance == null) {
            instance = new NumericValidator();
        }
        return instance;
    }

    public boolean validate(String input) {
        return input.matches("\\d+");
    }
}
