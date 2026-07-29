package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.dto.reservation.ReservationDto;
import com.example.InventoryManagement.dto.reservation.ReservationRequest;
import com.example.InventoryManagement.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

  private final ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ReservationDto create(@RequestBody ReservationRequest request) {
    return reservationService.create(request);
  }

  @GetMapping
  public List<ReservationDto> findAll() {
    return reservationService.findAll();
  }

  @GetMapping("/{id}")
  public ReservationDto findById(@PathVariable Long id) {
    return reservationService.findById(id);
  }

  @PutMapping("/{id}")
  public ReservationDto update(@PathVariable Long id,
                               @RequestBody ReservationRequest request) {
    return reservationService.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    reservationService.delete(id);
  }

  @GetMapping("/{id}/exists")
  public boolean exists(@PathVariable Long id) {
    return reservationService.exists(id);
  }

  @GetMapping("/count")
  public long count() {
    return reservationService.count();
  }

  @PostMapping("/reserve")
  @ResponseStatus(HttpStatus.OK)
  public void reserveStock(@RequestParam Long productId,
                           @RequestParam Integer quantity) {
    reservationService.reserveStock(productId, quantity);
  }

  @PostMapping("/release")
  @ResponseStatus(HttpStatus.OK)
  public void releaseStock(@RequestParam Long productId,
                           @RequestParam Integer quantity) {
    reservationService.releaseStock(productId, quantity);
  }
}