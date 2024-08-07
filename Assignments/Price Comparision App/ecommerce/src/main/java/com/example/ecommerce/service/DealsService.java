package com.example.ecommerce.service;

import com.example.ecommerce.model.DealItem;
import com.example.ecommerce.model.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class DealsService {

    @Autowired
    private AmazonService amazonService;

    @Autowired
    private EbayService ebayService;

    @Autowired
    private WalmartService walmartService;
    
    /*
     * These are the service instances for Amazon, eBay, and Walmart. 
     * The @Autowired annotation is used to inject these services into the DealsService class.
     */

    public ResponseWrapper getDeals(String categoryName) {
        CompletableFuture<List<DealItem>> amazonFuture = amazonService.getDeals(categoryName);
        CompletableFuture<List<DealItem>> ebayFuture = ebayService.getDeals(categoryName);
        CompletableFuture<List<DealItem>> walmartFuture = walmartService.getDeals(categoryName);

        List<DealItem> allDeals = CompletableFuture.allOf(amazonFuture, ebayFuture, walmartFuture)
                .thenApply(v -> {
                    List<DealItem> amazonDeals = null;
                    List<DealItem> ebayDeals = null;
                    List<DealItem> walmartDeals = null;

                    try {
                        amazonDeals = amazonFuture.join();
                    } catch (Exception e) {
                        System.err.println("Error fetching deals from Amazon: " + e.getMessage());
                    }

                    try {
                        ebayDeals = ebayFuture.join();
                    } catch (Exception e) {
                        System.err.println("Error fetching deals from eBay: " + e.getMessage());
                    }

                    try {
                        walmartDeals = walmartFuture.join();
                    } catch (Exception e) {
                        System.err.println("Error fetching deals from Walmart: " + e.getMessage());
                    }

                    System.out.println("Amazon Deals: " + amazonDeals);
                    System.out.println("eBay Deals: " + ebayDeals);
                    System.out.println("Walmart Deals: " + walmartDeals);

                    // Initialize a mutable list
                    List<DealItem> allValidDeals = new ArrayList<>();

                    if (amazonDeals != null) {
                        allValidDeals.addAll(amazonDeals.stream()
                                .filter(this::isValidDealWithLogging)
                                .collect(Collectors.toList()));
                    }

                    if (ebayDeals != null) {
                        allValidDeals.addAll(ebayDeals.stream()
                                .filter(this::isValidDealWithLogging)
                                .collect(Collectors.toList()));
                    }

                    if (walmartDeals != null) {
                        allValidDeals.addAll(walmartDeals.stream()
                                .filter(this::isValidDealWithLogging)
                                .collect(Collectors.toList()));
                    }

                    System.out.println("Valid deals count: " + allValidDeals.size());
                    System.out.println("Valid deals: " + allValidDeals);

                    return allValidDeals;
                })
                .join();

        List<DealItem> bestDeals = findBestDeals(allDeals);

        ResponseWrapper response = new ResponseWrapper();
        response.setCategoryName(categoryName);
        response.setDealItems(bestDeals);
        return response;
    }

    private boolean isValidDealWithLogging(DealItem dealItem) {
        boolean isValid = dealItem != null && dealItem.getPrice() > 0 && dealItem.getStock() > 0;
        if (!isValid) {
            System.out.println("Invalid deal item: " + dealItem);
        }
        return isValid;
    }

    private List<DealItem> findBestDeals(List<DealItem> allDeals) {
        // Logic to find the best deals based on price, discount, etc.
        // Example: Find the deal with the lowest price for each unique item.
        return allDeals.stream()
                .collect(Collectors.groupingBy(DealItem::getItemId))
                .values()
                .stream()
                .map(dealItems -> dealItems.stream()
                        .min((deal1, deal2) -> Double.compare(deal1.getPrice(), deal2.getPrice()))
                        .orElse(null))
                .collect(Collectors.toList());
    }
}
