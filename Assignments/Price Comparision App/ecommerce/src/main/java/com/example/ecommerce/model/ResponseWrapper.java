package com.example.ecommerce.model;

import java.util.List;

public class ResponseWrapper {
    private String categoryName;
    private List<DealItem> dealItems;

    // Getters
    public String getCategoryName() {
        return categoryName;
    }

    public List<DealItem> getDealItems() {
        return dealItems;
    }

    // Setters
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDealItems(List<DealItem> dealItems) {
        this.dealItems = dealItems;
    }
}
