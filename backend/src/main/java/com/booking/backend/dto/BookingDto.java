package com.booking.backend.dto;

import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Cuisine;
import com.booking.backend.entity.Reservation;

import java.util.List;

public record BookingDto(Reservation reservation,
                         UserDto userDto,
                         Long organizationId) {
}
