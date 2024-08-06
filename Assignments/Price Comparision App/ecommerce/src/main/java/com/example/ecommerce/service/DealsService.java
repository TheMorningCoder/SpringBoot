package com.example.ecommerce.service;

import com.example.ecommerce.model.DealItem;
import com.example.ecommerce.model.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public ResponseWrapper getDeals(String categoryName) {
        CompletableFuture<List<DealItem>> amazonFuture = amazonService.getDeals(categoryName);
        CompletableFuture<List<DealItem>> ebayFuture = ebayService.getDeals(categoryName);
        CompletableFuture<List<DealItem>> walmartFuture = walmartService.getDeals(categoryName);

        List<DealItem> allDeals = CompletableFuture.allOf(amazonFuture, ebayFuture, walmartFuture)
                .thenApply(v -> {
                    List<DealItem> amazonDeals = amazonFuture.join();
                    List<DealItem> ebayDeals = ebayFuture.join();
                    List<DealItem> walmartDeals = walmartFuture.join();

                    List<DealItem> allValidDeals = amazonDeals.stream()
                            .filter(this::isValidDeal)
                            .collect(Collectors.toList());
                    
                    allValidDeals.addAll(ebayDeals.stream()
                            .filter(this::isValidDeal)
                            .collect(Collectors.toList()));
                    
                    allValidDeals.addAll(walmartDeals.stream()
                            .filter(this::isValidDeal)
                            .collect(Collectors.toList()));
                    
                    return allValidDeals;
                })
                .join();

        List<DealItem> bestDeals = findBestDeals(allDeals);

        ResponseWrapper response = new ResponseWrapper();
        response.setCategoryName(categoryName);
        response.setDealItems(bestDeals);

        return response;
    }

    private boolean isValidDeal(DealItem deal) {
        return deal.getStock() > 0 && isWithinDateRange(deal.getDealStartDate(), deal.getDealEndDate());
    }

    private boolean isWithinDateRange(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        LocalDate now = LocalDate.now();
        
        return !now.isBefore(start) && !now.isAfter(end);
    }

    private List<DealItem> findBestDeals(List<DealItem> allDeals) {
        // Logic to find the best deals
        return allDeals.stream()
                .collect(Collectors.groupingBy(DealItem::getProductTitle))
                .values().stream()
                .map(this::getBestDeal)
                .collect(Collectors.toList());
    }

    private DealItem getBestDeal(List<DealItem> deals) {
        return deals.stream()
                .min((deal1, deal2) -> Double.compare(deal1.getPrice(), deal2.getPrice()))
                .orElseThrow(() -> new IllegalArgumentException("No deals found"));
    }
}
