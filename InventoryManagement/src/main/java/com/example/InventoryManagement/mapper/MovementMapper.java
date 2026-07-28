package com.example.InventoryManagement.mapper;

import com.example.InventoryManagement.dto.movement.MovementDto;
import com.example.InventoryManagement.dto.movement.MovementRequest;
import com.example.InventoryManagement.entity.Movement;
import com.example.InventoryManagement.entity.MovementType;
import com.example.InventoryManagement.entity.Product;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class MovementMapper {


  public MovementDto toDto(Movement movement) {

    return new MovementDto(
      movement.getId(),
      movement.getProduct().getId(),
      null,
      null,
      movement.getQuantity(),
      movement.getType(),
      movement.getCreatedAt()
    );
  }


  public Movement toEntity(MovementRequest request,
                           Product product) {

    Movement movement = new Movement();

    movement.setProduct(product);
    movement.setQuantity(request.getQuantity());
    movement.setType(MovementType.OUT);
    movement.setCreatedAt(OffsetDateTime.now());

    return movement;
  }


  public void updateEntity(MovementRequest request,
                           Movement movement,
                           Product product) {

    movement.setProduct(product);
    movement.setQuantity(request.getQuantity());

  }
}