package com.example.backendserver2.controller;

import com.example.backendserver2.model.DealCategory;
import com.example.backendserver2.service.DealCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backendserver2/ebay")
public class DealController {

    @Autowired
    private DealCategoryService dealCategoryService;

    @GetMapping("/deals/{categoryName}")
    public DealCategory getDealsByCategory(@PathVariable String categoryName) {
        return dealCategoryService.getDealsByCategory(categoryName);
    }
}
