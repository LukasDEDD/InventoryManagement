package com.example.InventoryManagement.service;

import com.example.InventoryManagement.entity.Reservation;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;

  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  public Reservation create(Reservation reservation) {
    return reservationRepository.save(reservation);
  }

  public Reservation update(Reservation reservation) {
    return reservationRepository.save(reservation);
  }

  public List<Reservation> findAll() {
    return reservationRepository.findAll();
  }

  public Reservation findById(Long id) {
    return reservationRepository.findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("Reservation with id " + id + " not found"));
  }

  public void delete(Long id) {
    reservationRepository.deleteById(id);
  }

  public boolean exists(Long id) {
    return reservationRepository.existsById(id);
  }

  public long count() {
    return reservationRepository.count();
  }
  public void reserveStock(Long productId, Integer quantity) {

  }

  public void releaseStock(Long productId, Integer quantity) {

  }
}