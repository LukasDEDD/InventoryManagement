package com.example.InventoryManagement.mapper;

import com.example.InventoryManagement.dto.product.ProductDto;
import com.example.InventoryManagement.dto.product.ProductRequest;
import com.example.InventoryManagement.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public ProductDto toDto(Product product) {
    return new ProductDto(
      product.getId(),
      product.getName(),
      product.getPrice()
    );
  }

  public Product toEntity(ProductRequest request) {
    Product product = new Product();
    product.setName(request.getName());
    product.setPrice(request.getPrice());
    return product;
  }

  public void updateEntity(ProductRequest request, Product product) {
    product.setName(request.getName());
    product.setPrice(request.getPrice());
  }
}
