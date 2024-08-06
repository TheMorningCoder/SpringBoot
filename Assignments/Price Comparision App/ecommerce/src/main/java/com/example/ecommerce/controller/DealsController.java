package com.example.ecommerce.controller;

import com.example.ecommerce.model.ResponseWrapper;
import com.example.ecommerce.service.DealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DealsController {

    @Autowired
    private DealsService dealsService;

    @GetMapping("/deals/{categoryName}")
    public ResponseWrapper getDeals(@PathVariable String categoryName) {
        return dealsService.getDeals(categoryName);
    }
}

