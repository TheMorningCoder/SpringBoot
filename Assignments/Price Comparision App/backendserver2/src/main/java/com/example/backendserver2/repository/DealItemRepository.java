package com.example.backendserver2.repository;

import com.example.backendserver2.model.DealCategory;
import com.example.backendserver2.model.DealItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealItemRepository extends JpaRepository<DealItem, String> {
    List<DealItem> findByDealCategory(DealCategory dealCategory);
}
