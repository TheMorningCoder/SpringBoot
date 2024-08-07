package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ResponseWrapper {
    @JsonProperty("categoryName")
    private String categoryName;

    @JsonProperty("dealItems")
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
