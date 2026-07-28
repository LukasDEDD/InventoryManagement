package com.example.InventoryManagement.mapper;

import com.example.InventoryManagement.dto.stockitem.StockItemDto;
import com.example.InventoryManagement.dto.stockitem.StockItemRequest;
import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.entity.StockItem;
import org.springframework.stereotype.Component;

@Component
public class StockItemMapper {

  public StockItemDto toDto(StockItem stockItem) {
    return new StockItemDto(
      stockItem.getId(),
      stockItem.getProduct().getId(),
      stockItem.getQuantity(),
      stockItem.getUpdatedAt()
    );
  }

  public StockItem toEntity(StockItemRequest request, Product product) {
    StockItem stockItem = new StockItem();
    stockItem.setProduct(product);
    stockItem.setQuantity(request.getQuantity());
    return stockItem;
  }

  public void updateEntity(StockItemRequest request, StockItem stockItem, Product product) {
    stockItem.setProduct(product);
    stockItem.setQuantity(request.getQuantity());
  }
}
