package com.example.InventoryManagement.service;

import com.example.InventoryManagement.dto.reservation.ReservationDto;
import com.example.InventoryManagement.dto.reservation.ReservationRequest;
import com.example.InventoryManagement.entity.Reservation;
import com.example.InventoryManagement.exception.ResourceNotFoundException;
import com.example.InventoryManagement.mapper.ReservationMapper;
import com.example.InventoryManagement.repository.ProductRepository;
import com.example.InventoryManagement.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final ProductRepository productRepository;
  private final ReservationMapper reservationMapper;

  public ReservationService(ReservationRepository reservationRepository,
                            ProductRepository productRepository,
                            ReservationMapper reservationMapper) {
    this.reservationRepository = reservationRepository;
    this.productRepository = productRepository;
    this.reservationMapper = reservationMapper;
  }

  public ReservationDto create(ReservationRequest request) {

    Reservation reservation = reservationMapper.toEntity(
      request,
      productRepository.findById(request.getProductId())
        .orElseThrow(() ->
          new ResourceNotFoundException(
            "Product with id " + request.getProductId() + " not found"))
    );

    return reservationMapper.toDto(reservationRepository.save(reservation));
  }

  public ReservationDto update(Long id, ReservationRequest request) {

    Reservation reservation = reservationRepository.findById(id)
      .orElseThrow(() ->
        new ResourceNotFoundException("Reservation with id " + id + " not found"));

    reservationMapper.updateEntity(
      request,
      reservation,
      productRepository.findById(request.getProductId())
        .orElseThrow(() ->
          new ResourceNotFoundException(
            "Product with id " + request.getProductId() + " not found"))
    );

    return reservationMapper.toDto(reservationRepository.save(reservation));
  }

  public List<ReservationDto> findAll() {

    return reservationRepository.findAll()
      .stream()
      .map(reservationMapper::toDto)
      .toList();
  }

  public ReservationDto findById(Long id) {

    return reservationMapper.toDto(
      reservationRepository.findById(id)
        .orElseThrow(() ->
          new ResourceNotFoundException("Reservation with id " + id + " not found"))
    );
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

    // TODO business logic

  }

  public void releaseStock(Long productId, Integer quantity) {

    // TODO business logic

  }
}