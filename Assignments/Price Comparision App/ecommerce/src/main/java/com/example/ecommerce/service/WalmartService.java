package com.example.ecommerce.service;

import com.example.ecommerce.model.DealItem;
import com.example.ecommerce.model.ResponseWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class WalmartService {

    private final WebClient webClient;

    public WalmartService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083").build();
    }

    public CompletableFuture<List<DealItem>> getDeals(String categoryName) {
        return webClient.get()
                .uri("/backendserver3/walmart/deals/{categoryName}", categoryName)
                .retrieve()
                .bodyToMono(ResponseWrapper.class)
                .map(ResponseWrapper::getDealItems)
                //.doOnNext(deals -> System.out.println("Walmart Service fetched deals: " + deals))
                .toFuture();
    }
}
