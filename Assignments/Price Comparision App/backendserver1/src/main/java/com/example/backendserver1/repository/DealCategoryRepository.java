package com.example.backendserver1.repository;

import com.example.backendserver1.model.DealCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealCategoryRepository extends JpaRepository<DealCategory, Long> {
    DealCategory findByCategoryName(String categoryName);
}
