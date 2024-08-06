package com.example.ecommerce.controller;

import com.example.ecommerce.model.DealItem;
import com.example.ecommerce.model.ResponseWrapper;
import com.example.ecommerce.service.AmazonService;
import com.example.ecommerce.service.DealsService;
import com.example.ecommerce.service.EbayService;
import com.example.ecommerce.service.WalmartService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class DealsServiceTest {

    @Mock
    private AmazonService amazonService;

    @Mock
    private EbayService ebayService;

    @Mock
    private WalmartService walmartService;

    @InjectMocks
    private DealsService dealsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDeals() throws Exception {
        // Mock data
        DealItem amazonDeal1 = createDealItem("1", "ProductA", 100.0, 10);
        DealItem amazonDeal2 = createDealItem("2", "ProductB", 200.0, 5);
        DealItem ebayDeal1 = createDealItem("3", "ProductA", 90.0, 10);
        DealItem walmartDeal1 = createDealItem("4", "ProductC", 300.0, 2);

        // Mock the services
        when(amazonService.getDeals(anyString())).thenReturn(CompletableFuture.completedFuture(Arrays.asList(amazonDeal1, amazonDeal2)));
        when(ebayService.getDeals(anyString())).thenReturn(CompletableFuture.completedFuture(Arrays.asList(ebayDeal1)));
        when(walmartService.getDeals(anyString())).thenReturn(CompletableFuture.completedFuture(Arrays.asList(walmartDeal1)));

        // Call the service
        ResponseWrapper response = dealsService.getDeals("Electronics");

        // Validate the response
        assertEquals("Electronics", response.getCategoryName());
        List<DealItem> dealItems = response.getDealItems();
        assertEquals(3, dealItems.size());

        // Check that the best deals were selected
        assertEquals("ProductA", dealItems.get(0).getProductTitle());
        assertEquals(90.0, dealItems.get(0).getPrice());
        assertEquals("ProductB", dealItems.get(1).getProductTitle());
        assertEquals(200.0, dealItems.get(1).getPrice());
        assertEquals("ProductC", dealItems.get(2).getProductTitle());
        assertEquals(300.0, dealItems.get(2).getPrice());
    }

    private DealItem createDealItem(String itemId, String title, double price, int stock) {
        DealItem dealItem = new DealItem();
        dealItem.setItemId(itemId);
        dealItem.setProductTitle(title);
        dealItem.setPrice(price);
        dealItem.setStock(stock);
        dealItem.setDealStartDate("2023-01-01");
        dealItem.setDealEndDate("2025-12-31");
        return dealItem;
    }
}
