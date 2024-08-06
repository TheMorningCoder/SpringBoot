package com.example.backendserver1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class DealItem {

    @Id
    private String itemid;

    private String productTitle;

    private String size;

    private String brand;

    private String imageUrl;

    private BigDecimal originalPrice;

    private double discountPercentage;

    private BigDecimal discountAmount;

    private BigDecimal price;

    private String priceTreatment;

    private int stock;

    private LocalDateTime dealStartDate;

    private LocalDateTime dealEndDate;

    @ManyToOne
    @JoinColumn(name = "dealCategory_id")
    @JsonBackReference
    private DealCategory dealCategory;

    // Default constructor
    public DealItem() {}

    // Parameterized constructor
    public DealItem(String itemid, String productTitle, String size, String brand, String imageUrl,
                    BigDecimal originalPrice, double discountPercentage, BigDecimal discountAmount,
                    BigDecimal price, String priceTreatment, int stock,
                    LocalDateTime dealStartDate, LocalDateTime dealEndDate) {
        this.itemid = itemid;
        this.productTitle = productTitle;
        this.size = size;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.originalPrice = originalPrice;
        this.discountPercentage = discountPercentage;
        this.discountAmount = discountAmount;
        this.price = price;
        this.priceTreatment = priceTreatment;
        this.stock = stock;
        this.dealStartDate = dealStartDate;
        this.dealEndDate = dealEndDate;
    }

    // Getters and Setters
    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPriceTreatment() {
        return priceTreatment;
    }

    public void setPriceTreatment(String priceTreatment) {
        this.priceTreatment = priceTreatment;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getDealStartDate() {
        return dealStartDate;
    }

    public void setDealStartDate(LocalDateTime dealStartDate) {
        this.dealStartDate = dealStartDate;
    }

    public LocalDateTime getDealEndDate() {
        return dealEndDate;
    }

    public void setDealEndDate(LocalDateTime dealEndDate) {
        this.dealEndDate = dealEndDate;
    }

    public DealCategory getDealCategory() {
        return dealCategory;
    }

    public void setDealCategory(DealCategory dealCategory) {
        this.dealCategory = dealCategory;
    }
}
