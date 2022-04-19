package com.booking.backend.mapper;

import com.booking.backend.dto.ReservationDto;
import com.booking.backend.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapperImpl implements ReservationMapper {

    private SomeObjectMapper someObjectMapper;
    private BookingMapper bookingMapper;

    public ReservationMapperImpl(SomeObjectMapper someObjectMapper, BookingMapper bookingMapper) {
        this.someObjectMapper = someObjectMapper;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public Reservation convertFromReservationDtoToReservation(ReservationDto reservationDto) {
        return new Reservation(reservationDto.id(), reservationDto.beginning(), reservationDto.ending(),
                reservationDto.date(),
                someObjectMapper.convertFromSomeObjectDtoToSomeObject(reservationDto.someObjectDto()),
                bookingMapper.convertFromBookingDtoToBooking(reservationDto.bookingDto()));
    }

    @Override
    public ReservationDto convertFromReservationToReservationDto(Reservation reservation) {
        return null;
    }
}
