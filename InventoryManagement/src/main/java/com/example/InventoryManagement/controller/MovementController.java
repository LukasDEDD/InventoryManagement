package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.dto.movement.MovementDto;
import com.example.InventoryManagement.dto.movement.MovementRequest;
import com.example.InventoryManagement.service.MovementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
public class MovementController {

  private final MovementService movementService;

  public MovementController(MovementService movementService) {
    this.movementService = movementService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MovementDto create(@RequestBody MovementRequest request) {
    return movementService.create(request);
  }

  @GetMapping
  public List<MovementDto> findAll() {
    return movementService.findAll();
  }

  @GetMapping("/{id}")
  public MovementDto findById(@PathVariable Long id) {
    return movementService.findById(id);
  }

  @PutMapping("/{id}")
  public MovementDto update(@PathVariable Long id,
                            @RequestBody MovementRequest request) {
    return movementService.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    movementService.delete(id);
  }

  @GetMapping("/{id}/exists")
  public boolean exists(@PathVariable Long id) {
    return movementService.exists(id);
  }

  @GetMapping("/count")
  public long count() {
    return movementService.count();
  }

  @PostMapping("/move")
  @ResponseStatus(HttpStatus.OK)
  public void moveStock(@RequestParam Long productId,
                        @RequestParam Integer quantity) {
    movementService.moveStock(productId, quantity);
  }
}