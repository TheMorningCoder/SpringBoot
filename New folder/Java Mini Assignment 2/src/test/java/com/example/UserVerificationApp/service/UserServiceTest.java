package com.example.UserVerificationApp.service;

import com.example.UserVerificationApp.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private ApiService apiService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchUsersInParallel() {
        List<String> userIds = List.of("1", "2", "3");
        CompletableFuture<List<User>> futureUsers = userService.fetchUsersInParallel(userIds);
        List<User> users = futureUsers.join();

        assertEquals(3, users.size());
        assertEquals("1", users.get(0).getId());
    }

    @Test
    public void testFetchUserById() {
        CompletableFuture<User> futureUser = userService.fetchUserById("1");
        User user = futureUser.join();

        assertEquals("1", user.getId());
    }

    @Test
    public void testFetchUserDetailsFromApi() {
        String userId = "1";
        String expectedResponse = "User Details";
        when(apiService.callExternalApi("https://api.example.com/users/" + userId)).thenReturn(expectedResponse);

        CompletableFuture<String> futureResponse = userService.fetchUserDetailsFromApi(userId);
        String response = futureResponse.join();

        assertEquals(expectedResponse, response);
    }
}
