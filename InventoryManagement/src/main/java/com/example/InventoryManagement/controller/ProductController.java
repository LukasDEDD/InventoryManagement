package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.dto.product.ProductDto;
import com.example.InventoryManagement.dto.product.ProductRequest;
import com.example.InventoryManagement.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductDto create(@RequestBody ProductRequest request) {
    return productService.create(request);
  }

  @GetMapping
  public List<ProductDto> findAll() {
    return productService.findAll();
  }

  @GetMapping("/{id}")
  public ProductDto findById(@PathVariable Long id) {
    return productService.findById(id);
  }

  @PutMapping("/{id}")
  public ProductDto update(@PathVariable Long id,
                           @RequestBody ProductRequest request) {
    return productService.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    productService.delete(id);
  }

  @GetMapping("/{id}/exists")
  public boolean exists(@PathVariable Long id) {
    return productService.exists(id);
  }

  @GetMapping("/count")
  public long count() {
    return productService.count();
  }
}