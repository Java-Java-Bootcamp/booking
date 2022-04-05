package com.booking.backend.repository;

import com.booking.backend.entity.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {
}
