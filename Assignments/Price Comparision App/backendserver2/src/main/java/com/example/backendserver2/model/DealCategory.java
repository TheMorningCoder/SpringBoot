package com.example.backendserver2.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class DealCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @OneToMany(mappedBy = "dealCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
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
