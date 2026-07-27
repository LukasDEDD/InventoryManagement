package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.StockItem;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.repository.StockItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockItemService {

  private final StockItemRepository stockItemRepository;

  public StockItemService(StockItemRepository stockItemRepository) {
    this.stockItemRepository = stockItemRepository;
  }

  public StockItem create(StockItem stockItem) {
    return stockItemRepository.save(stockItem);
  }

  public StockItem update(StockItem stockItem) {
    return stockItemRepository.save(stockItem);
  }

  public List<StockItem> findAll() {
    return stockItemRepository.findAll();
  }

  public StockItem findById(Long id) {
    return stockItemRepository.findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("Stock item with id " + id + " not found"));
  }

  public void delete(Long id) {
    stockItemRepository.deleteById(id);
  }

  public boolean exists(Long id) {
    return stockItemRepository.existsById(id);
  }

  public long count() {
    return stockItemRepository.count();
  }
}