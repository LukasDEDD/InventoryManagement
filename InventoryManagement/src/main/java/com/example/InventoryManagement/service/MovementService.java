package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.movement.MovementDto;
import com.example.InventoryManagement.dto.movement.MovementRequest;
import com.example.InventoryManagement.entity.Movement;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.mapper.MovementMapper;
import com.example.InventoryManagement.repository.MovementRepository;
import com.example.InventoryManagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService {

  private final MovementRepository movementRepository;
  private final ProductRepository productRepository;
  private final MovementMapper movementMapper;

  public MovementService(MovementRepository movementRepository,
                         ProductRepository productRepository,
                         MovementMapper movementMapper) {
    this.movementRepository = movementRepository;
    this.productRepository = productRepository;
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


  }
}