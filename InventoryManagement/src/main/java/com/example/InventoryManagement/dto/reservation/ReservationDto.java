package com.example.InventoryManagement.dto.reservation;

import com.example.InventoryManagement.entity.ReservationStatus;

public class ReservationDto {

  private Long id;
  private Long productId;
  private Integer quantity;
  private ReservationStatus status;

  public ReservationDto() {
  }

  public ReservationDto(Long id, Long productId, Integer quantity, ReservationStatus status) {
    this.id = id;
    this.productId = productId;
    this.quantity = quantity;
    this.status = status;
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

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public ReservationStatus getStatus() {
    return status;
  }

  public void setStatus(ReservationStatus status) {
    this.status = status;
  }
}