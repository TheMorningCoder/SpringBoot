package com.example.backendserver1.config;

import com.example.backendserver1.model.DealCategory;
import com.example.backendserver1.model.DealItem;
import com.example.backendserver1.repository.DealCategoryRepository;
import com.example.backendserver1.repository.DealItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
public class DataInitializer {

    @Autowired
    private DealCategoryRepository dealCategoryRepository;

    @Autowired
    private DealItemRepository dealItemRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            // Categories and their items
            addCategoryWithItems("Jeans", new DealItem[]{
                new DealItem(
                    "123456789",
                    "Blue Levis Jeans",
                    "30",
                    "Levis",
                    "https://i.ebayimg.com/images/g/~**********N/s-l225.jpg",
                    new BigDecimal("29.99"),
                    17.0,
                    new BigDecimal("5.0"),
                    new BigDecimal("24.99"),
                    "LIST_PRICE",
                    4,
                    LocalDateTime.parse("2022-06-20T15:26:00"),
                    LocalDateTime.parse("2022-12-20T14:59:59")
                ),
                new DealItem(
                    "981235678",
                    "Black Men Slim Fit Mid Rise Jeans",
                    "32",
                    "Louis Philippe Jeans",
                    "https://i.ebayimg.com/images/g/~**********N/s-l225.jpg",
                    new BigDecimal("29.99"),
                    50.0,
                    new BigDecimal("14.99"),
                    new BigDecimal("14.99"),
                    "LIST_PRICE",
                    3,
                    LocalDateTime.parse("2023-06-20T15:26:00"),
                    LocalDateTime.parse("2023-12-20T14:59:59")
                )
            });

            addCategoryWithItems("Smartphones", new DealItem[]{
                new DealItem(
                    "234567890",
                    "Samsung Galaxy S23",
                    "128GB",
                    "Samsung",
                    "https://example.com/images/samsung_galaxy_s23.jpg",
                    new BigDecimal("799.99"),
                    10.0,
                    new BigDecimal("80.00"),
                    new BigDecimal("719.99"),
                    "LIST_PRICE",
                    20,
                    LocalDateTime.parse("2024-08-01T10:00:00"),
                    LocalDateTime.parse("2024-11-01T23:59:59")
                ),
                new DealItem(
                    "345678901",
                    "iPhone 14 Pro",
                    "256GB",
                    "Apple",
                    "https://example.com/images/iphone_14_pro.jpg",
                    new BigDecimal("999.99"),
                    12.0,
                    new BigDecimal("120.00"),
                    new BigDecimal("879.99"),
                    "LIST_PRICE",
                    15,
                    LocalDateTime.parse("2024-08-01T10:00:00"),
                    LocalDateTime.parse("2024-11-01T23:59:59")
                )
            });

            addCategoryWithItems("Refrigerators", new DealItem[]{
                new DealItem(
                    "456789012",
                    "LG 260L Double Door Refrigerator",
                    "260L",
                    "LG",
                    "https://example.com/images/lg_refrigerator.jpg",
                    new BigDecimal("499.99"),
                    8.0,
                    new BigDecimal("40.00"),
                    new BigDecimal("459.99"),
                    "LIST_PRICE",
                    10,
                    LocalDateTime.parse("2024-08-01T10:00:00"),
                    LocalDateTime.parse("2024-11-01T23:59:59")
                ),
                new DealItem(
                    "567890123",
                    "Samsung 300L Side by Side Refrigerator",
                    "300L",
                    "Samsung",
                    "https://example.com/images/samsung_refrigerator.jpg",
                    new BigDecimal("699.99"),
                    15.0,
                    new BigDecimal("105.00"),
                    new BigDecimal("594.99"),
                    "LIST_PRICE",
                    8,
                    LocalDateTime.parse("2024-08-01T10:00:00"),
                    LocalDateTime.parse("2024-11-01T23:59:59")
                )
            });
        };
    }

    private void addCategoryWithItems(String categoryName, DealItem[] items) {
        Optional<DealCategory> existingCategory = dealCategoryRepository.findByCategoryName(categoryName);
        DealCategory category;
        if (existingCategory.isPresent()) {
            category = existingCategory.get();
        } else {
            category = new DealCategory();
            category.setCategoryName(categoryName);
            category = dealCategoryRepository.save(category);
        }

        for (DealItem item : items) {
            item.setDealCategory(category);
            if (!dealItemRepository.existsById(item.getItemid())) {
                dealItemRepository.save(item);
            }
        }
    }
}
