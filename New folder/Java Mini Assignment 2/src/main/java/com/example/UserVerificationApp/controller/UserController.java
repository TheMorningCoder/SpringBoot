package com.example.UserVerificationApp.controller;

import com.example.UserVerificationApp.entity.User;
import com.example.UserVerificationApp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public CompletableFuture<User> getUserById(@PathVariable String id) {
        return userService.fetchUserById(id);
    }

    @GetMapping("/users")
    public CompletableFuture<List<User>> getAllUsers() {
        // Implement logic to get all users, for simplicity returning an empty list
        return userService.fetchUsersInParallel(List.of("1", "2", "3"));
    }

    @GetMapping("/users/details/{id}")
    public CompletableFuture<String> getUserDetailsFromApi(@PathVariable String id) {
        return userService.fetchUserDetailsFromApi(id);
    }
}
