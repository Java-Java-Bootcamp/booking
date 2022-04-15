package com.booking.backend.mapper;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Person;

public interface BookingMapper {

    BookingDto convertFromBookingToBookingDto(Booking booking);
    Booking convertFromBookingDtoToBooking(BookingDto bookingDto);

    Person convertFromPersonDtoToPerson(PersonDto personDto);
}
