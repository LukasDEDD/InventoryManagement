package com.example.InventoryManagement;

import com.example.InventoryManagement.dto.reservation.ReservationDto;
import com.example.InventoryManagement.dto.reservation.ReservationRequest;
import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.entity.Reservation;
import com.example.InventoryManagement.entity.ReservationStatus;
import com.example.InventoryManagement.entity.StockItem;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.mapper.ReservationMapper;
import com.example.InventoryManagement.repository.ProductRepository;
import com.example.InventoryManagement.repository.ReservationRepository;
import com.example.InventoryManagement.repository.StockItemRepository;
import com.example.InventoryManagement.service.ReservationService;
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
class ReservationServiceTest {

  @Mock
  private ReservationRepository reservationRepository;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private StockItemRepository stockItemRepository;

  @Mock
  private ReservationMapper reservationMapper;

  @InjectMocks
  private ReservationService reservationService;


  private Product product;
  private Reservation reservation;
  private ReservationDto reservationDto;
  private ReservationRequest request;
  private StockItem stockItem;


  @BeforeEach
  void setUp() {

    product = new Product();
    product.setId(1L);
    product.setName("Notebook");

    stockItem = new StockItem();
    stockItem.setId(1L);
    stockItem.setProduct(product);
    stockItem.setQuantity(100);

    reservation = new Reservation();
    reservation.setId(1L);
    reservation.setProduct(product);
    reservation.setQuantity(10);
    reservation.setStatus(ReservationStatus.ACTIVE);

    reservationDto = new ReservationDto();
    reservationDto.setId(1L);
    reservationDto.setQuantity(10);

    request = new ReservationRequest();
    request.setProductId(1L);
    request.setQuantity(10);
  }


  @Test
  void create_shouldCreateReservation() {

    when(productRepository.findById(1L))
      .thenReturn(Optional.of(product));

    when(reservationMapper.toEntity(request,
      product))
      .thenReturn(reservation);

    when(reservationRepository.save(reservation))
      .thenReturn(reservation);

    when(reservationMapper.toDto(reservation))
      .thenReturn(reservationDto);

    ReservationDto result =
      reservationService.create(request);

    assertNotNull(result);
    assertEquals(1L,
      result.getId());
    assertEquals(10,
      result.getQuantity());

    verify(productRepository)
      .findById(1L);

    verify(reservationMapper)
      .toEntity(request,
        product);

    verify(reservationRepository)
      .save(reservation);

    verify(reservationMapper)
      .toDto(reservation);
  }

  @Test
  void create_shouldThrowExceptionWhenProductNotFound() {

    when(productRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> reservationService.create(request));

    verify(productRepository)
      .findById(1L);

    verify(reservationRepository,
      never())
      .save(any());
  }

  @Test
  void update_shouldUpdateReservation() {

    when(reservationRepository.findById(1L))
      .thenReturn(Optional.of(reservation));

    when(productRepository.findById(1L))
      .thenReturn(Optional.of(product));

    when(reservationRepository.save(reservation))
      .thenReturn(reservation);

    when(reservationMapper.toDto(reservation))
      .thenReturn(reservationDto);

    ReservationDto result =
      reservationService.update(1L,
        request);

    assertNotNull(result);

    verify(reservationRepository)
      .findById(1L);

    verify(productRepository)
      .findById(1L);

    verify(reservationMapper)
      .updateEntity(request,
        reservation,
        product);

    verify(reservationRepository)
      .save(reservation);

    verify(reservationMapper)
      .toDto(reservation);
  }

  @Test
  void update_shouldThrowExceptionWhenReservationNotFound() {

    when(reservationRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> reservationService.update(1L,
        request));

    verify(reservationRepository)
      .findById(1L);

    verify(productRepository,
      never())
      .findById(any());
  }

  @Test
  void update_shouldThrowExceptionWhenProductNotFound() {

    when(reservationRepository.findById(1L))
      .thenReturn(Optional.of(reservation));

    when(productRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> reservationService.update(1L,
        request));

    verify(reservationRepository)
      .findById(1L);

    verify(productRepository)
      .findById(1L);

    verify(reservationRepository,
      never())
      .save(any());
  }

  @Test
  void findById_shouldReturnReservation() {

    when(reservationRepository.findById(1L))
      .thenReturn(Optional.of(reservation));

    when(reservationMapper.toDto(reservation))
      .thenReturn(reservationDto);

    ReservationDto result =
      reservationService.findById(1L);

    assertEquals(1L,
      result.getId());

    verify(reservationRepository)
      .findById(1L);

    verify(reservationMapper)
      .toDto(reservation);
  }

  @Test
  void findById_shouldThrowExceptionWhenNotFound() {

    when(reservationRepository.findById(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> reservationService.findById(1L));

    verify(reservationRepository)
      .findById(1L);
  }

  @Test
  void findAll_shouldReturnReservations() {

    when(reservationRepository.findAll())
      .thenReturn(List.of(reservation));

    when(reservationMapper.toDto(reservation))
      .thenReturn(reservationDto);

    List<ReservationDto> result =
      reservationService.findAll();

    assertEquals(1,
      result.size());

    verify(reservationRepository)
      .findAll();

    verify(reservationMapper)
      .toDto(reservation);
  }

  @Test
  void delete_shouldDeleteReservation() {
    reservationService.delete(1L);
    verify(reservationRepository)
      .deleteById(1L);
  }

  @Test
  void exists_shouldReturnTrue() {

    when(reservationRepository.existsById(1L))
      .thenReturn(true);
    assertTrue(reservationService.exists(1L));
    verify(reservationRepository)
      .existsById(1L);
  }


  @Test
  void exists_shouldReturnFalse() {

    when(reservationRepository.existsById(1L))
      .thenReturn(false);
    assertFalse(reservationService.exists(1L));
    verify(reservationRepository)
      .existsById(1L);
  }

  @Test
  void count_shouldReturnNumberOfReservations() {

    when(reservationRepository.count())
      .thenReturn(20L);
    assertEquals(20L,
      reservationService.count());
    verify(reservationRepository)
      .count();
  }

  @Test
  void reserveStock_shouldReserveStockSuccessfully() {

    when(stockItemRepository.findByProductId(1L))
      .thenReturn(Optional.of(stockItem));

    reservationService.reserveStock(1L,
      20);

    assertEquals(80,
      stockItem.getQuantity());

    verify(stockItemRepository)
      .save(stockItem);

    verify(reservationRepository)
      .save(any(Reservation.class));
  }

  @Test
  void reserveStock_shouldThrowExceptionWhenStockItemNotFound() {

    when(stockItemRepository.findByProductId(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> reservationService.reserveStock(1L,
        20));

    verify(stockItemRepository)
      .findByProductId(1L);

    verify(reservationRepository,
      never())
      .save(any());
  }


  @Test
  void reserveStock_shouldThrowExceptionWhenNotEnoughStock() {

    stockItem.setQuantity(5);

    when(stockItemRepository.findByProductId(1L))
      .thenReturn(Optional.of(stockItem));

    assertThrows(IllegalArgumentException.class,
      () -> reservationService.reserveStock(1L,
        20));

    verify(stockItemRepository)
      .findByProductId(1L);

    verify(stockItemRepository,
      never())
      .save(any());

    verify(reservationRepository,
      never())
      .save(any());
  }

  @Test
  void releaseStock_shouldReleaseStockSuccessfully() {

    reservation.setStatus(ReservationStatus.ACTIVE);

    when(reservationRepository.findByProductId(1L))
      .thenReturn(Optional.of(reservation));

    when(stockItemRepository.findByProductId(1L))
      .thenReturn(Optional.of(stockItem));

    int oldQuantity = stockItem.getQuantity();

    reservationService.releaseStock(1L,
      20);

    assertEquals(
      oldQuantity + 20,
      stockItem.getQuantity()
    );

    assertEquals(
      ReservationStatus.CANCELLED,
      reservation.getStatus()
    );

    verify(stockItemRepository)
      .save(stockItem);

    verify(reservationRepository)
      .save(reservation);
  }


  @Test
  void releaseStock_shouldThrowExceptionWhenReservationNotFound() {

    when(reservationRepository.findByProductId(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> reservationService.releaseStock(1L,
        20));

    verify(reservationRepository)
      .findByProductId(1L);

    verify(stockItemRepository,
      never())
      .findByProductId(any());
  }

  @Test
  void releaseStock_shouldThrowExceptionWhenStockItemNotFound() {

    when(reservationRepository.findByProductId(1L))
      .thenReturn(Optional.of(reservation));

    when(stockItemRepository.findByProductId(1L))
      .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class,
      () -> reservationService.releaseStock(1L,
        20));

    verify(reservationRepository)
      .findByProductId(1L);

    verify(stockItemRepository)
      .findByProductId(1L);

    verify(stockItemRepository,
      never())
      .save(any());

    verify(reservationRepository,
      never())
      .save(any());
  }
}
