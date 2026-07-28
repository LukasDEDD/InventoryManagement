package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.stockitem.StockItemDto;
import com.example.InventoryManagement.dto.stockitem.StockItemRequest;
import com.example.InventoryManagement.entity.StockItem;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.mapper.StockItemMapper;
import com.example.InventoryManagement.repository.ProductRepository;
import com.example.InventoryManagement.repository.StockItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockItemService {

  private final StockItemRepository stockItemRepository;
  private final ProductRepository productRepository;
  private final StockItemMapper stockItemMapper;

  public StockItemService(StockItemRepository stockItemRepository,
                          ProductRepository productRepository,
                          StockItemMapper stockItemMapper) {
    this.stockItemRepository = stockItemRepository;
    this.productRepository = productRepository;
    this.stockItemMapper = stockItemMapper;
  }

  public StockItemDto create(StockItemRequest request) {

    StockItem stockItem = stockItemMapper.toEntity(
      request,
      productRepository.findById(request.getProductId())
        .orElseThrow(() ->
          new ResourceNotFoundException(
            "Product with id " + request.getProductId() + " not found"))
    );

    return stockItemMapper.toDto(stockItemRepository.save(stockItem));
  }

  public StockItemDto update(Long id, StockItemRequest request) {

    StockItem stockItem = stockItemRepository.findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("StockItem with id " + id + " not found"));

    stockItemMapper.updateEntity(
      request,
      stockItem,
      productRepository.findById(request.getProductId())
        .orElseThrow(() ->
          new ResourceNotFoundException(
            "Product with id " + request.getProductId() + " not found"))
    );

    return stockItemMapper.toDto(stockItemRepository.save(stockItem));
  }

  public List<StockItemDto> findAll() {

    return stockItemRepository.findAll()
      .stream()
      .map(stockItemMapper::toDto)
      .toList();
  }

  public StockItemDto findById(Long id) {

    return stockItemMapper.toDto(
      stockItemRepository.findById(id)
        .orElseThrow(() ->
          new ResourceNotFoundException("StockItem with id " + id + " not found"))
    );
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