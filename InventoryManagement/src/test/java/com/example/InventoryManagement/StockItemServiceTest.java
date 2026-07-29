package com.example.InventoryManagement;

import com.example.InventoryManagement.dto.stockitem.StockItemDto;
import com.example.InventoryManagement.dto.stockitem.StockItemRequest;
import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.entity.StockItem;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.mapper.StockItemMapper;
import com.example.InventoryManagement.repository.ProductRepository;
import com.example.InventoryManagement.repository.StockItemRepository;
import com.example.InventoryManagement.service.StockItemService;
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
class StockItemServiceTest {

  @Mock
  private StockItemRepository stockItemRepository;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private StockItemMapper stockItemMapper;

  @InjectMocks
  private StockItemService stockItemService;

  private Product product;
  private StockItem stockItem;
  private StockItemDto stockItemDto;
  private StockItemRequest request;

  @BeforeEach
  void setUp() {

    product = new Product();
    product.setId(1L);
    product.setName("Notebook");

    stockItem = new StockItem();
    stockItem.setId(1L);
    stockItem.setProduct(product);
    stockItem.setQuantity(100);

    stockItemDto = new StockItemDto();
    stockItemDto.setId(1L);
    stockItemDto.setQuantity(100);

    request = new StockItemRequest();
    request.setProductId(1L);
    request.setQuantity(100);
  }

  @Test
  void create_shouldCreateStockItem() {

    when(productRepository.findById(1L))
      .thenReturn(Optional.of(product));

    when(stockItemMapper.toEntity(request, product))
      .thenReturn(stockItem);

    when(stockItemRepository.save(stockItem))
      .thenReturn(stockItem);

    when(stockItemMapper.toDto(stockItem))
      .thenReturn(stockItemDto);

    StockItemDto result = stockItemService.create(request);

    assertNotNull(result);
    assertEquals(stockItemDto.getId(), result.getId());
    assertEquals(stockItemDto.getQuantity(), result.getQuantity());

    verify(productRepository).findById(1L);
    verify(stockItemMapper).toEntity(request, product);
    verify(stockItemRepository).save(stockItem);
    verify(stockItemMapper).toDto(stockItem);
  }

  @Test
  void create_shouldThrowExceptionWhenProductNotFound() {

    when(productRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> stockItemService.create(request));

    verify(productRepository).findById(1L);
    verify(stockItemRepository, never()).save(any());
  }

  @Test
  void update_shouldUpdateStockItem() {

    when(stockItemRepository.findById(1L))
      .thenReturn(Optional.of(stockItem));

    when(productRepository.findById(1L))
      .thenReturn(Optional.of(product));

    when(stockItemRepository.save(stockItem))
      .thenReturn(stockItem);

    when(stockItemMapper.toDto(stockItem))
      .thenReturn(stockItemDto);

    StockItemDto result = stockItemService.update(1L, request);

    assertNotNull(result);

    verify(stockItemRepository).findById(1L);
    verify(productRepository).findById(1L);
    verify(stockItemMapper).updateEntity(request, stockItem, product);
    verify(stockItemRepository).save(stockItem);
    verify(stockItemMapper).toDto(stockItem);
  }

  @Test
  void update_shouldThrowExceptionWhenStockItemNotFound() {

    when(stockItemRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> stockItemService.update(1L, request));

    verify(stockItemRepository).findById(1L);
    verify(productRepository, never()).findById(any());
  }

  @Test
  void update_shouldThrowExceptionWhenProductNotFound() {

    when(stockItemRepository.findById(1L))
      .thenReturn(Optional.of(stockItem));

    when(productRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> stockItemService.update(1L, request));

    verify(stockItemRepository).findById(1L);
    verify(productRepository).findById(1L);
    verify(stockItemRepository, never()).save(any());
  }

  @Test
  void findById_shouldReturnStockItem() {

    when(stockItemRepository.findById(1L))
      .thenReturn(Optional.of(stockItem));

    when(stockItemMapper.toDto(stockItem))
      .thenReturn(stockItemDto);

    StockItemDto result = stockItemService.findById(1L);

    assertEquals(stockItemDto.getId(), result.getId());

    verify(stockItemRepository).findById(1L);
    verify(stockItemMapper).toDto(stockItem);
  }

  @Test
  void findById_shouldThrowExceptionWhenNotFound() {

    when(stockItemRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> stockItemService.findById(1L));

    verify(stockItemRepository).findById(1L);
  }

  @Test
  void findAll_shouldReturnAllStockItems() {

    when(stockItemRepository.findAll())
      .thenReturn(List.of(stockItem));

    when(stockItemMapper.toDto(stockItem))
      .thenReturn(stockItemDto);

    List<StockItemDto> result = stockItemService.findAll();

    assertEquals(1, result.size());
    assertEquals(stockItemDto.getQuantity(), result.get(0).getQuantity());

    verify(stockItemRepository).findAll();
    verify(stockItemMapper).toDto(stockItem);
  }

  @Test
  void delete_shouldDeleteStockItem() {

    stockItemService.delete(1L);

    verify(stockItemRepository).deleteById(1L);
  }

  @Test
  void exists_shouldReturnTrue() {

    when(stockItemRepository.existsById(1L))
      .thenReturn(true);

    assertTrue(stockItemService.exists(1L));

    verify(stockItemRepository).existsById(1L);
  }

  @Test
  void exists_shouldReturnFalse() {

    when(stockItemRepository.existsById(1L))
      .thenReturn(false);

    assertFalse(stockItemService.exists(1L));

    verify(stockItemRepository).existsById(1L);
  }

  @Test
  void count_shouldReturnNumberOfStockItems() {

    when(stockItemRepository.count())
      .thenReturn(10L);

    assertEquals(10L, stockItemService.count());

    verify(stockItemRepository).count();
  }
}
