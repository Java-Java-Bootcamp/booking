package com.booking.backend.service;

import com.booking.backend.dto.ReservationDto;

public interface ReservationService {
    void updateWithNewBooking(ReservationDto reservationDto);
}
