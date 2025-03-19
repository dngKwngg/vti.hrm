package com.example.vti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vti.entities.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}
