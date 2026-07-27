package com.example.InventoryManagement.service;


import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {

    this.productRepository = productRepository;
  }

  public Product create(Product product) {

    return productRepository.save(product);
  }

  public Product update(Product product) {

    return productRepository.save(product);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Product findById(Long id) {
    return productRepository.findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("Product with id " + id + " not found"));
  }

  public void delete(Long id) {
    productRepository.deleteById(id);
  }

  public boolean exists(Long id) {
    return productRepository.existsById(id);
  }

  public long count() {
    return productRepository.count();
  }
}