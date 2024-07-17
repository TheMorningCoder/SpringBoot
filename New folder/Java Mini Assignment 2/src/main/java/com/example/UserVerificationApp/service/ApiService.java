//package com.example.UserVerificationApp.service;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Component
//public class ApiService {
//    private final WebClient webClient;
//
//    public ApiService(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.build();
//    }
//
//    public User fetchRandomUser() {
//        // Call API to fetch random user
//    }
//
//    public String fetchNationality(String name) {
//        // Call API to fetch nationality
//    }
//
//    public String fetchGender(String name) {
//        // Call API to fetch gender
//    }
//}
package com.example.UserVerificationApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callExternalApi(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
