package com.example.backendserver1.service;

import com.example.backendserver1.model.DealCategory;
import com.example.backendserver1.model.DealItem;
import com.example.backendserver1.repository.DealCategoryRepository;
import com.example.backendserver1.repository.DealItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealCategoryService {

    @Autowired
    private DealItemRepository dealItemRepository;

    @Autowired
    private DealCategoryRepository dealCategoryRepository;

    public DealCategory getDealsByCategory(String categoryName) {
        // Fetch the DealCategory by name
        Optional<DealCategory> optionalDealCategory = dealCategoryRepository.findByCategoryName(categoryName);
        DealCategory dealCategory;
        if (optionalDealCategory.isPresent()) {
            dealCategory = optionalDealCategory.get();
        } else {
            dealCategory = new DealCategory(categoryName, List.of());
        }

        // Fetch the items by category
        List<DealItem> filteredItems = dealItemRepository.findByDealCategory(dealCategory);

        // Set the filtered items to the DealCategory and return
        dealCategory.setDealItems(filteredItems);
        return dealCategory;
    }
}
