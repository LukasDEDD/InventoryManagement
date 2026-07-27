package com.example.InventoryManagement.entity;


import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(nullable = false)
  private Integer quantity;

  @Column(name = "created_at")
  private OffsetDateTime createdAt;

  @Column(nullable = false)
  private String status;

  public Reservation() {
  }

  public Long getId() {
    return id;
  }

  public Product getProduct() {
    return product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public String getStatus() {
    return status;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
