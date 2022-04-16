package com.booking.backend.mapper;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;

public interface BookingMapper {

    BookingDto convertFromBookingToBookingDto(Booking booking);

    Booking convertFromBookingDtoToBooking(BookingDto bookingDto);
}
