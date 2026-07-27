package com.example.InventoryManagement.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "movement")
public class Movement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private Integer quantity;

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  public Movement() {
  }

  public Long getId() {
    return id;
  }

  public Product getProduct() {
    return product;
  }

  public String getType() {
    return type;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
