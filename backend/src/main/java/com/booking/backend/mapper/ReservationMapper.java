package com.booking.backend.mapper;

import com.booking.backend.dto.ReservationDto;
import com.booking.backend.entity.Reservation;

public interface ReservationMapper {

    Reservation convertFromReservationDtoToReservation(ReservationDto reservationDto);

    ReservationDto convertFromReservationToReservationDto(Reservation reservation);
}
