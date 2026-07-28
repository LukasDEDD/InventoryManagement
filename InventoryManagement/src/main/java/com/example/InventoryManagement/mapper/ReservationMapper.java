package com.example.InventoryManagement.mapper;

import com.example.InventoryManagement.dto.reservation.ReservationDto;
import com.example.InventoryManagement.dto.reservation.ReservationRequest;
import com.example.InventoryManagement.entity.Product;
import com.example.InventoryManagement.entity.Reservation;
import com.example.InventoryManagement.entity.ReservationStatus;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ReservationMapper {

  public ReservationDto toDto(Reservation reservation) {

    return new ReservationDto(
      reservation.getId(),
      reservation.getProduct().getId(),
      reservation.getQuantity(),
      reservation.getStatus()
    );
  }


  public Reservation toEntity(ReservationRequest request, Product product) {

    Reservation reservation = new Reservation();

    reservation.setProduct(product);
    reservation.setQuantity(request.getQuantity());
    reservation.setStatus(ReservationStatus.ACTIVE);
    reservation.setCreatedAt(OffsetDateTime.now());

    return reservation;
  }


  public void updateEntity(ReservationRequest request,
                           Reservation reservation,
                           Product product) {

    reservation.setProduct(product);
    reservation.setQuantity(request.getQuantity());

  }
}