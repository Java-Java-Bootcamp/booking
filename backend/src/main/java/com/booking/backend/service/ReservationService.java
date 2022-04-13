package com.booking.backend.service;

import com.booking.backend.dto.ReservationDto;

import java.util.Optional;

public interface ReservationService {

    ReservationDto findReservationById(Long id);
}
