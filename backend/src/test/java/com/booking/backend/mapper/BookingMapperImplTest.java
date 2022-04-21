package com.booking.backend.mapper;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Booking;
import com.booking.backend.entity.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingMapperImplTest {

    private BookingMapperImpl bookingMapper = new BookingMapperImpl(new PersonMapperImpl());

    private BookingDto bookingDtoEx = new BookingDto(1L, new PersonDto(1L, "Alex"));
    private Booking bookingEx = new Booking(1L, new Person(1L, "Alex"));

    @Test
    void convertFromBookingToBookingDto() {
        var bookingDto
                = bookingMapper.convertFromBookingToBookingDto(bookingEx);
        assertEquals(bookingDtoEx, bookingDto);
    }

    @Test
    void convertFromBookingDtoToBooking() {
        var booking = bookingMapper.convertFromBookingDtoToBooking(bookingDtoEx);
        assertEquals(bookingEx, booking);
    }
}