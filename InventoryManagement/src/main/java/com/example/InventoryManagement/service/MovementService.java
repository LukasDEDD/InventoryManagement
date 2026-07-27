package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.Movement;
import com.example.InventoryManagement.repository.MovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService {

  private final MovementRepository movementRepository;

  public MovementService(MovementRepository movementRepository) {
    this.movementRepository = movementRepository;
  }

  public Movement create(Movement movement) {
    return movementRepository.save(movement);
  }

  public Movement update(Movement movement) {
    return movementRepository.save(movement);
  }

  public List<Movement> findAll() {
    return movementRepository.findAll();
  }

  public Movement findById(Long id) {
    return movementRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Movement not found"));
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
}