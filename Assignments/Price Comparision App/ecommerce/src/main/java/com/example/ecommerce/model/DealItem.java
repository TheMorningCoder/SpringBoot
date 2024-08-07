package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DealItem {
    @JsonProperty("itemid")
    private String itemId;

    @JsonProperty("productTitle")
    private String productTitle;

    @JsonProperty("size")
    private String size;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("originalPrice")
    private double originalPrice;

    @JsonProperty("discountPercentage")
    private double discountPercentage;

    @JsonProperty("price")
    private double price;

    @JsonProperty("stock")
    private int stock;

    @JsonProperty("dealStartDate")
    private String dealStartDate;

    @JsonProperty("dealEndDate")
    private String dealEndDate;

    // Constructor
    public DealItem(String itemId, String productTitle, String size, String brand, String imageUrl, double originalPrice, double discountPercentage, double price, int stock, String dealStartDate, String dealEndDate) {
        this.itemId = itemId;
        this.productTitle = productTitle;
        this.size = size;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.originalPrice = originalPrice;
        this.discountPercentage = discountPercentage;
        this.price = price;
        this.stock = stock;
        this.dealStartDate = dealStartDate;
        this.dealEndDate = dealEndDate;
    }

    public DealItem() {
    }

    // Getters
    public String getItemId() {
        return itemId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getSize() {
        return size;
    }

    public String getBrand() {
        return brand;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getDealStartDate() {
        return dealStartDate;
    }

    public String getDealEndDate() {
        return dealEndDate;
    }

    // Setters
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setDealStartDate(String dealStartDate) {
        this.dealStartDate = dealStartDate;
    }

    public void setDealEndDate(String dealEndDate) {
        this.dealEndDate = dealEndDate;
    }

    @Override
    public String toString() {
        return "DealItem{" +
                "productTitle='" + productTitle + '\'' +
                ", stock=" + stock +
                ", dealStartDate='" + dealStartDate + '\'' +
                ", dealEndDate='" + dealEndDate + '\'' +
                ", price=" + price +
                '}';
    }
}
