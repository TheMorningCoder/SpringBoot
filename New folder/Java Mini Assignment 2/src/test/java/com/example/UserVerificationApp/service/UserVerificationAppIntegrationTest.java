package com.example.UserVerificationApp.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.example.UserVerificationApp.entity.User;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserVerificationAppIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateUser() {
        String url = "/users?size=3";
        ResponseEntity<User[]> response = restTemplate.postForEntity(url, null, User[].class);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    public void testGetUsers() {
        String url = "/users?sortType=Name&sortOrder=EVEN&limit=3&offset=0";
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
        assertEquals(200, response.getStatusCodeValue());
    }
}
