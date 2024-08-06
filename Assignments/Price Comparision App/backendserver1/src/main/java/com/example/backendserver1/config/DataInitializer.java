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
            // Check if the category already exists to avoid redundant data
            Optional<DealCategory> existingCategory = dealCategoryRepository.findByCategoryName("Jeans");
            DealCategory jeansCategory;
            if (existingCategory.isPresent()) {
                jeansCategory = existingCategory.get();
            } else {
                // Create and save DealCategory
                jeansCategory = new DealCategory();
                jeansCategory.setCategoryName("Jeans");
                jeansCategory = dealCategoryRepository.save(jeansCategory);
            }

            // Create DealItem objects with unique IDs
            DealItem item1 = new DealItem(
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
            );
            item1.setDealCategory(jeansCategory);

            DealItem item2 = new DealItem(
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
            );
            item2.setDealCategory(jeansCategory);

            // Check for existing DealItems to avoid duplicates
            if (!dealItemRepository.existsById(item1.getItemid())) {
                dealItemRepository.save(item1);
            }

            if (!dealItemRepository.existsById(item2.getItemid())) {
                dealItemRepository.save(item2);
            }
        };
    }
}
