package com.booking.backend.dto;

import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Cuisine;
import com.booking.backend.entity.Reservation;

import java.util.List;

public record BookingDto(Long id,
                         String organizationName,
                         Double rate,
                         Double bill,
                         List<Cuisine> cuisineOfOrganization,
                         List<Reservation> slotsOfReservation) {
}
