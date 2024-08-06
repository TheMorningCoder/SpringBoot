package com.example.backendserver1.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class DealCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dealCategory_id")
    private List<DealItem> dealItems;

    // Default constructor
    public DealCategory() {}

    // Parameterized constructor
    public DealCategory(String categoryName, List<DealItem> dealItems) {
        this.categoryName = categoryName;
        this.dealItems = dealItems;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<DealItem> getDealItems() {
        return dealItems;
    }

    public void setDealItems(List<DealItem> dealItems) {
        this.dealItems = dealItems;
    }
}
