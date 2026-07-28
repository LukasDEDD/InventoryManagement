package com.example.InventoryManagement.dto.stockitem;

import java.time.OffsetDateTime;

public class StockItemDto {

  private Long id;
  private Long productId;
  private Integer quantity;
  private OffsetDateTime updatedAt;

  public StockItemDto() {
  }

  public StockItemDto(Long id, Long productId, Integer quantity, OffsetDateTime updatedAt) {
    this.id = id;
    this.productId = productId;
    this.quantity = quantity;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public Long getProductId() {
    return productId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}