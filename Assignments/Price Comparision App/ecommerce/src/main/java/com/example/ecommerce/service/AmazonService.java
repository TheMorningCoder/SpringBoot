package com.example.ecommerce.service;

import com.example.ecommerce.model.DealItem;
import com.example.ecommerce.model.ResponseWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AmazonService {

    private final WebClient webClient;

    public AmazonService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public CompletableFuture<List<DealItem>> getDeals(String categoryName) {
        return webClient.get()
                .uri("/backendserver1/amazon/deals/{categoryName}", categoryName)
                .retrieve()
                .bodyToMono(ResponseWrapper.class)
                .map(ResponseWrapper::getDealItems)
                //.doOnNext(deals -> System.out.println("Amazon Service fetched deals: " + deals))
                .toFuture();
    }
}
