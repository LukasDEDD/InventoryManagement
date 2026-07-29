package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.dto.stockitem.StockItemDto;
import com.example.InventoryManagement.dto.stockitem.StockItemRequest;
import com.example.InventoryManagement.service.StockItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-items")
public class StockItemController {

  private final StockItemService stockItemService;

  public StockItemController(StockItemService stockItemService) {
    this.stockItemService = stockItemService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public StockItemDto create(@RequestBody StockItemRequest request) {
    return stockItemService.create(request);
  }

  @GetMapping
  public List<StockItemDto> findAll() {
    return stockItemService.findAll();
  }

  @GetMapping("/{id}")
  public StockItemDto findById(@PathVariable Long id) {
    return stockItemService.findById(id);
  }

  @PutMapping("/{id}")
  public StockItemDto update(@PathVariable Long id,
                             @RequestBody StockItemRequest request) {
    return stockItemService.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    stockItemService.delete(id);
  }

  @GetMapping("/{id}/exists")
  public boolean exists(@PathVariable Long id) {
    return stockItemService.exists(id);
  }

  @GetMapping("/count")
  public long count() {
    return stockItemService.count();
  }
}
