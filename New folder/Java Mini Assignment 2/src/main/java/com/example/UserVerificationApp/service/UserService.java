package com.example.UserVerificationApp.service;

import com.example.UserVerificationApp.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UserService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final ApiService apiService;

    public UserService(ApiService apiService) {
        this.apiService = apiService;
    }

    public CompletableFuture<List<User>> fetchUsersInParallel(List<String> userIds) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate fetching users from database or API
            // Replace with actual logic
            return userIds.stream().map(id -> new User(id, "Name" + id, "Email" + id, "Password" + id)).toList();
        }, executorService);
    }

    public CompletableFuture<User> fetchUserById(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate fetching a user by ID
            // Replace with actual logic
            return new User(userId, "Name" + userId, "Email" + userId, "Password" + userId);
        }, executorService);
    }

    public CompletableFuture<String> fetchUserDetailsFromApi(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            String apiUrl = "https://api.example.com/users/" + userId;
            return apiService.callExternalApi(apiUrl);
        }, executorService);
    }
}
