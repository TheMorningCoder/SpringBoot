package com.example.ecommerce.model;

public class DealItem {
    private String itemId;
    private String productTitle;
    private String size;
    private String brand;
    private String imageUrl;
    private double originalPrice;
    private double discountPercentage;
    private double price;
    private int stock;
    private String dealStartDate;
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
}
