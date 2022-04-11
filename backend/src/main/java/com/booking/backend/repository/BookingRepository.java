package com.booking.backend.repository;

import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> getAllByBookedTrueAndOrganizationName(String name);

//    List<Booking> getAllByOrganizationNameAndOrganizationRating(String name);
//    List<Booking> findTop20B(String name);
}
