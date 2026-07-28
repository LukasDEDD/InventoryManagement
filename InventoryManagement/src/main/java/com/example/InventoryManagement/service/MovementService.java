package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.movement.MovementDto;
import com.example.InventoryManagement.dto.movement.MovementRequest;
import com.example.InventoryManagement.entity.Movement;
import com.example.InventoryManagement.entity.Reservation;
import com.example.InventoryManagement.entity.ReservationStatus;
import com.example.InventoryManagement.entity.StockItem;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.mapper.MovementMapper;
import com.example.InventoryManagement.repository.MovementRepository;
import com.example.InventoryManagement.repository.ProductRepository;
import com.example.InventoryManagement.repository.ReservationRepository;
import com.example.InventoryManagement.repository.StockItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService {

  private final MovementRepository movementRepository;
  private final ProductRepository productRepository;
  private final StockItemRepository stockItemRepository;
  private final ReservationRepository reservationRepository;
  private final MovementMapper movementMapper;

  public MovementService(MovementRepository movementRepository,
                         ProductRepository productRepository,
                         StockItemRepository stockItemRepository,
                         ReservationRepository reservationRepository,
                         MovementMapper movementMapper) {
    this.movementRepository = movementRepository;
    this.productRepository = productRepository;
    this.stockItemRepository = stockItemRepository;
    this.reservationRepository = reservationRepository;
    this.movementMapper = movementMapper;
  }

  public MovementDto create(MovementRequest request) {

    Movement movement = movementMapper.toEntity(
      request,
      productRepository.findById(request.getProductId())
        .orElseThrow(() ->
          new ResourceNotFoundException(
            "Product with id " + request.getProductId() + " not found"))
    );

    return movementMapper.toDto(movementRepository.save(movement));
  }

  public MovementDto update(Long id, MovementRequest request) {

    Movement movement = movementRepository.findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("Movement with id " + id + " not found"));

    movementMapper.updateEntity(
      request,
      movement,
      productRepository.findById(request.getProductId())
        .orElseThrow(() ->
          new ResourceNotFoundException(
            "Product with id " + request.getProductId() + " not found"))
    );

    return movementMapper.toDto(movementRepository.save(movement));
  }

  public List<MovementDto> findAll() {
    return movementRepository.findAll()
      .stream()
      .map(movementMapper::toDto)
      .toList();
  }

  public MovementDto findById(Long id) {

    return movementMapper.toDto(
      movementRepository.findById(id)
        .orElseThrow(() ->
          new ResourceNotFoundException("Movement with id " + id + " not found"))
    );
  }

  public void delete(Long id) {
    movementRepository.deleteById(id);
  }

  public boolean exists(Long id) {
    return movementRepository.existsById(id);
  }

  public long count() {
    return movementRepository.count();
  }

  public void moveStock(Long productId, Integer quantity) {

    Reservation reservation = reservationRepository.findByProductId(productId)
      .orElseThrow(() -> new ResourceNotFoundException(
        "Reservation not found for product id: " + productId));


    StockItem stockItem = stockItemRepository.findByProductId(productId)
      .orElseThrow(() -> new ResourceNotFoundException(
        "Stock item not found for product id: " + productId));

    stockItem.setQuantity(stockItem.getQuantity() + quantity);
    stockItemRepository.save(stockItem);


    reservation.setStatus(ReservationStatus.CANCELLED);
    reservationRepository.save(reservation);

  }
}