package com.example.InventoryManagement.dto.movement;

import com.example.InventoryManagement.entity.MovementType;

import java.time.OffsetDateTime;

public class MovementDto {

  private Long id;
  private Long productId;
  private Long fromWarehouseId;
  private Long toWarehouseId;
  private Integer quantity;
  private MovementType type;
  private OffsetDateTime createdAt;

  public MovementDto() {
  }

  public MovementDto(Long id,
                     Long productId,
                     Long fromWarehouseId,
                     Long toWarehouseId,
                     Integer quantity,
                     MovementType type,
                     OffsetDateTime createdAt) {
    this.id = id;
    this.productId = productId;
    this.fromWarehouseId = fromWarehouseId;
    this.toWarehouseId = toWarehouseId;
    this.quantity = quantity;
    this.type = type;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getFromWarehouseId() {
    return fromWarehouseId;
  }

  public void setFromWarehouseId(Long fromWarehouseId) {
    this.fromWarehouseId = fromWarehouseId;
  }

  public Long getToWarehouseId() {
    return toWarehouseId;
  }

  public void setToWarehouseId(Long toWarehouseId) {
    this.toWarehouseId = toWarehouseId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public MovementType getType() {
    return type;
  }

  public void setType(MovementType type) {
    this.type = type;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }
}