package com.booking.backend.dto;

import com.booking.backend.entity.Booking;
import com.booking.backend.entity.SomeObject;

public record ReservationDto(Long id,
                             Integer beginning,
                             Integer ending,
                             String date,
                             SomeObject someObject,
                             Booking booking) {
}
