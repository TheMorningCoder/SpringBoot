package com.example.backendserver3.controller;

import com.example.backendserver3.model.DealCategory;
import com.example.backendserver3.service.DealCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backendserver3/walmart")
public class DealController {

    @Autowired
    private DealCategoryService dealCategoryService;

    @GetMapping("/deals/{categoryName}")
    public DealCategory getDealsByCategory(@PathVariable String categoryName) {
        return dealCategoryService.getDealsByCategory(categoryName);
    }
}
