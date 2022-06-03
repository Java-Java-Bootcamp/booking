package com.booking.backend.usecases;

import com.booking.backend.entities.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation convert(ReservationDto reservationDto);

    ReservationDto convert(Reservation reservation);
}
