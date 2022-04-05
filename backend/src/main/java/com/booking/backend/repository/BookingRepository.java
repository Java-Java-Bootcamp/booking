package com.booking.backend.repository;

import com.booking.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> getAllByBookedFalseAndOrganizationName(String name);
}
