package com.booking.backend.mapper;

import com.booking.backend.dto.ReservationDto;
import com.booking.backend.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation convert (ReservationDto reservationDto);

    ReservationDto convert (Reservation reservation);
}
