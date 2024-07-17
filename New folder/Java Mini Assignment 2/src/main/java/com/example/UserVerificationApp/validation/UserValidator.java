package com.example.UserVerificationApp.validation;

import com.example.UserVerificationApp.entity.User;
import com.example.UserVerificationApp.exception.ValidationException;

public class UserValidator {

    public void validateUser(User user) throws ValidationException {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new ValidationException("User name is required.");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException("User email is required.");
        }
        // Add more validation logic as needed
    }
}
