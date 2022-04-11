package com.booking.backend.repository;

import com.booking.backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservRepo extends JpaRepository<Reservation, Long> {
}
