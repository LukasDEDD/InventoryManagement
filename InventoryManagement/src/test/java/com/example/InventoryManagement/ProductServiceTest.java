package com.example.InventoryManagement;

import com.example.InventoryManagement.dto.product.ProductDto;
import com.example.InventoryManagement.dto.product.ProductRequest;
import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.mapper.ProductMapper;
import com.example.InventoryManagement.repository.ProductRepository;
import com.example.InventoryManagement.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ProductMapper productMapper;

  @InjectMocks
  private ProductService productService;

  private Product product;
  private ProductDto productDto;
  private ProductRequest request;

  @BeforeEach
  void setUp() {

    product = new Product();
    product.setId(1L);
    product.setName("Notebook");

    productDto = new ProductDto();
    productDto.setId(1L);
    productDto.setName("Notebook");

    request = new ProductRequest();
    request.setName("Notebook");
  }

  @Test
  void create_shouldCreateProduct() {

    when(productMapper.toEntity(request)).thenReturn(product);
    when(productRepository.save(product)).thenReturn(product);
    when(productMapper.toDto(product)).thenReturn(productDto);

    ProductDto result = productService.create(request);

    assertNotNull(result);
    assertEquals(productDto.getId(), result.getId());
    assertEquals(productDto.getName(), result.getName());

    verify(productMapper).toEntity(request);
    verify(productRepository).save(product);
    verify(productMapper).toDto(product);
  }

  @Test
  void findById_shouldReturnProduct() {

    when(productRepository.findById(1L))
      .thenReturn(Optional.of(product));

    when(productMapper.toDto(product))
      .thenReturn(productDto);

    ProductDto result = productService.findById(1L);

    assertEquals(productDto.getId(), result.getId());

    verify(productRepository).findById(1L);
    verify(productMapper).toDto(product);
  }

  @Test
  void findById_shouldThrowExceptionWhenProductNotFound() {

    when(productRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> productService.findById(1L));

    verify(productRepository).findById(1L);
    verifyNoInteractions(productMapper);
  }

  @Test
  void findAll_shouldReturnAllProducts() {

    when(productRepository.findAll())
      .thenReturn(List.of(product));

    when(productMapper.toDto(product))
      .thenReturn(productDto);

    List<ProductDto> result = productService.findAll();

    assertEquals(1, result.size());
    assertEquals(productDto.getName(), result.get(0).getName());

    verify(productRepository).findAll();
    verify(productMapper).toDto(product);
  }

  @Test
  void update_shouldUpdateProduct() {

    when(productRepository.findById(1L))
      .thenReturn(Optional.of(product));

    when(productRepository.save(product))
      .thenReturn(product);

    when(productMapper.toDto(product))
      .thenReturn(productDto);

    ProductDto result = productService.update(1L, request);

    assertNotNull(result);

    verify(productRepository).findById(1L);
    verify(productMapper).updateEntity(request, product);
    verify(productRepository).save(product);
    verify(productMapper).toDto(product);
  }

  @Test
  void update_shouldThrowExceptionWhenProductNotFound() {

    when(productRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> productService.update(1L, request));

    verify(productRepository).findById(1L);
    verify(productRepository, never()).save(any());
  }

  @Test
  void delete_shouldDeleteProduct() {

    productService.delete(1L);

    verify(productRepository).deleteById(1L);
  }

  @Test
  void exists_shouldReturnTrue() {

    when(productRepository.existsById(1L))
      .thenReturn(true);

    assertTrue(productService.exists(1L));

    verify(productRepository).existsById(1L);
  }

  @Test
  void exists_shouldReturnFalse() {

    when(productRepository.existsById(1L))
      .thenReturn(false);

    assertFalse(productService.exists(1L));

    verify(productRepository).existsById(1L);
  }

  @Test
  void count_shouldReturnNumberOfProducts() {

    when(productRepository.count())
      .thenReturn(5L);

    assertEquals(5L, productService.count());

    verify(productRepository).count();
  }
}