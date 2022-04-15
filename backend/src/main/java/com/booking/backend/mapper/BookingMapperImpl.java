package com.booking.backend.mapper;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;
import com.booking.backend.mapper.BookingMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingDto convertFromBookingToBookingDto(Booking booking) {
        return new BookingDto(booking.getId(), null, null);
    }

    @Override
    public Booking convertFromBookingDtoToBooking(BookingDto bookingDto) {
        return new Booking(bookingDto.id(), 0, null);
    }
}
