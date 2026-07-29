package com.example.InventoryManagement;

import com.example.InventoryManagement.dto.movement.MovementDto;
import com.example.InventoryManagement.dto.movement.MovementRequest;
import com.example.InventoryManagement.entity.Movement;
import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.entity.Reservation;
import com.example.InventoryManagement.entity.StockItem;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.mapper.MovementMapper;
import com.example.InventoryManagement.repository.MovementRepository;
import com.example.InventoryManagement.repository.ProductRepository;
import com.example.InventoryManagement.repository.ReservationRepository;
import com.example.InventoryManagement.repository.StockItemRepository;
import com.example.InventoryManagement.service.MovementService;
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
class MovementServiceTest {

  @Mock
  private MovementRepository movementRepository;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private StockItemRepository stockItemRepository;

  @Mock
  private ReservationRepository reservationRepository;

  @Mock
  private MovementMapper movementMapper;

  @InjectMocks
  private MovementService movementService;

  private Product product;
  private Movement movement;
  private MovementDto movementDto;
  private MovementRequest request;
  private Reservation reservation;
  private StockItem stockItem;



  @BeforeEach
  void setUp() {

    product = new Product();
    product.setId(1L);
    product.setName("Notebook");

    movement = new Movement();
    movement.setId(1L);
    movement.setProduct(product);

    movementDto = new MovementDto();
    movementDto.setId(1L);

    request = new MovementRequest();
    request.setProductId(1L);

    reservation = new Reservation();
    reservation.setId(1L);
    reservation.setProduct(product);

    stockItem = new StockItem();
    stockItem.setId(1L);
    stockItem.setProduct(product);
    stockItem.setQuantity(50);
  }

  @Test
  void create_shouldCreateMovement() {

    when(productRepository.findById(1L))
      .thenReturn(Optional.of(product));

    when(movementMapper.toEntity(request, product))
      .thenReturn(movement);

    when(movementRepository.save(movement))
      .thenReturn(movement);

    when(movementMapper.toDto(movement))
      .thenReturn(movementDto);

    MovementDto result =
      movementService.create(request);

    assertNotNull(result);
    assertEquals(1L, result.getId());

    verify(productRepository)
      .findById(1L);

    verify(movementMapper)
      .toEntity(request, product);

    verify(movementRepository)
      .save(movement);

    verify(movementMapper)
      .toDto(movement);
  }

  @Test
  void create_shouldThrowExceptionWhenProductNotFound() {

    when(productRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> movementService.create(request));

    verify(productRepository)
      .findById(1L);

    verify(movementRepository, never())
      .save(any());
  }

  @Test
  void update_shouldUpdateMovement() {


    when(movementRepository.findById(1L))
      .thenReturn(Optional.of(movement));


    when(productRepository.findById(1L))
      .thenReturn(Optional.of(product));


    when(movementRepository.save(movement))
      .thenReturn(movement);

    when(movementMapper.toDto(movement))
      .thenReturn(movementDto);

    MovementDto result =
      movementService.update(1L, request);

    assertNotNull(result);

    verify(movementRepository)
      .findById(1L);

    verify(productRepository)
      .findById(1L);

    verify(movementMapper)
      .updateEntity(request, movement, product);

    verify(movementRepository)
      .save(movement);

    verify(movementMapper)
      .toDto(movement);
  }

  @Test
  void update_shouldThrowExceptionWhenMovementNotFound() {

    when(movementRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> movementService.update(1L, request));

    verify(movementRepository)
      .findById(1L);

    verify(productRepository, never())
      .findById(any());
  }

  @Test
  void update_shouldThrowExceptionWhenProductNotFound() {

    when(movementRepository.findById(1L))
      .thenReturn(Optional.of(movement));

    when(productRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> movementService.update(1L, request));

    verify(productRepository)
      .findById(1L);

    verify(movementRepository, never())
      .save(any());
  }

  @Test
  void findById_shouldReturnMovement() {

    when(movementRepository.findById(1L))
      .thenReturn(Optional.of(movement));

    when(movementMapper.toDto(movement))
      .thenReturn(movementDto);

    MovementDto result =
      movementService.findById(1L);

    assertEquals(1L, result.getId());

    verify(movementRepository)
      .findById(1L);

    verify(movementMapper)
      .toDto(movement);
  }

  @Test
  void findById_shouldThrowExceptionWhenNotFound() {

    when(movementRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> movementService.findById(1L));

    verify(movementRepository)
      .findById(1L);
  }

  @Test
  void findAll_shouldReturnMovements() {


    when(movementRepository.findAll())
      .thenReturn(List.of(movement));

    when(movementMapper.toDto(movement))
      .thenReturn(movementDto);

    List<MovementDto> result =
      movementService.findAll();

    assertEquals(1, result.size());

    verify(movementRepository)
      .findAll();

    verify(movementMapper)
      .toDto(movement);
  }

  @Test
  void delete_shouldDeleteMovement() {

    movementService.delete(1L);

    verify(movementRepository)
      .deleteById(1L);
  }

  @Test
  void exists_shouldReturnTrue() {


    when(movementRepository.existsById(1L))
      .thenReturn(true);

    assertTrue(movementService.exists(1L));

    verify(movementRepository)
      .existsById(1L);
  }

  @Test
  void exists_shouldReturnFalse() {

    when(movementRepository.existsById(1L))
      .thenReturn(false);

    assertFalse(movementService.exists(1L));

    verify(movementRepository)
      .existsById(1L);
  }

  @Test
  void count_shouldReturnNumberOfMovements() {

    when(movementRepository.count())
      .thenReturn(15L);

    assertEquals(15L,
      movementService.count());

    verify(movementRepository)
      .count();
  }

  @Test
  void moveStock_shouldMoveStockSuccessfully() {


    when(reservationRepository.findByProductId(1L))
      .thenReturn(Optional.of(reservation));

    when(stockItemRepository.findByProductId(1L))
      .thenReturn(Optional.of(stockItem));

    int oldQuantity = stockItem.getQuantity();

    movementService.moveStock(1L, 20);

    assertEquals(
      oldQuantity + 20,
      stockItem.getQuantity()
    );

    assertEquals(
      com.example.InventoryManagement.entity.ReservationStatus.CANCELLED,
      reservation.getStatus()
    );

    verify(stockItemRepository)
      .save(stockItem);

    verify(reservationRepository)
      .save(reservation);
  }

  @Test
  void moveStock_shouldThrowExceptionWhenReservationNotFound() {


    when(reservationRepository.findByProductId(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> movementService.moveStock(1L, 20));

    verify(reservationRepository)
      .findByProductId(1L);

    verify(stockItemRepository, never())
      .findByProductId(any());
  }

  @Test
  void moveStock_shouldThrowExceptionWhenStockItemNotFound() {

    when(reservationRepository.findByProductId(1L))
      .thenReturn(Optional.of(reservation));

    when(stockItemRepository.findByProductId(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> movementService.moveStock(1L, 20));

    verify(reservationRepository)
      .findByProductId(1L);

    verify(stockItemRepository)
      .findByProductId(1L);

    verify(stockItemRepository, never())
      .save(any());

    verify(reservationRepository, never())
      .save(any());
  }
}
