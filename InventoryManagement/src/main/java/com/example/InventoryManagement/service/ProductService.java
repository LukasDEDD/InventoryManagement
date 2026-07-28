package com.example.InventoryManagement.service;


import com.example.InventoryManagement.dto.product.ProductDto;
import com.example.InventoryManagement.dto.product.ProductRequest;
import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.mapper.ProductMapper;
import com.example.InventoryManagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public ProductService(ProductRepository productRepository,
                        ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  public ProductDto create(ProductRequest request) {

    Product product = productMapper.toEntity(request);

    Product saved = productRepository.save(product);

    return productMapper.toDto(saved);
  }

  public ProductDto findById(Long id) {

    Product product = productRepository.findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("Product with id " + id + " not found"));

    return productMapper.toDto(product);
  }

  public List<ProductDto> findAll() {

    return productRepository.findAll()
      .stream()
      .map(productMapper::toDto)
      .toList();
  }

  public ProductDto update(Long id, ProductRequest request) {

    Product product = productRepository.findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("Product with id " + id + " not found"));

    productMapper.updateEntity(request, product);

    return productMapper.toDto(productRepository.save(product));
  }

  public void delete(Long id) {
    productRepository.deleteById(id);
  }

  public boolean exists(Long id) {
    return productRepository.existsById(id);
  }

  public long count()  {
    return productRepository.count();
  }
}