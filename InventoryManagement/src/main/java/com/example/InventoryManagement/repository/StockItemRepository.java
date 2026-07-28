package com.example.InventoryManagement.repository;


import com.example.InventoryManagement.entity.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {
  Optional<StockItem> findByProductId(Long productId);

}

