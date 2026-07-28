package com.example.InventoryManagement.dto.stockitem;

public class StockItemRequest {

  private Long productId;
  private Integer quantity;

  public StockItemRequest() {
  }

  public StockItemRequest(Long productId, Integer quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public Long getProductId() {
    return productId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}