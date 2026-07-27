package com.example.InventoryManagement.repository;


import com.example.InventoryManagement.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long> {

}