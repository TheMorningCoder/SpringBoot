package com.example.ecommerce.service;

import com.example.ecommerce.model.DealItem;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class EbayService {

    private final WebClient webClient;

    public EbayService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    public CompletableFuture<List<DealItem>> getDeals(String categoryName) {
        return webClient.get()
                .uri("/backendserver2/ebay/deals/{categoryName}", categoryName)
                .retrieve()
                .bodyToFlux(DealItem.class)
                .collectList()
                .toFuture();
    }
}
