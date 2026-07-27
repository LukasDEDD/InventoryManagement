package com.example.InventoryManagement.repository;


import com.example.InventoryManagement.entity.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {

}

