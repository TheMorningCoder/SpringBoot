package com.example.backendserver3.repository;

import com.example.backendserver3.model.DealCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DealCategoryRepository extends JpaRepository<DealCategory, Long> {
    Optional<DealCategory> findByCategoryName(String categoryName);
}
