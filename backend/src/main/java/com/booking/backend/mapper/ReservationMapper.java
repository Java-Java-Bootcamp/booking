package com.booking.backend.mapper;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.dto.ReservationDto;
import com.booking.backend.entity.Person;
import com.booking.backend.entity.Reservation;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ReservationMapper {

    Reservation convert (ReservationDto reservationDto);

    ReservationDto convert (Reservation reservation);
}
