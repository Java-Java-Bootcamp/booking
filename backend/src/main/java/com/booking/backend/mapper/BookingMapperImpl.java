package com.booking.backend.mapper;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapperImpl implements BookingMapper {

    private PersonMapper personMapper;

    public BookingMapperImpl(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    public BookingDto convertFromBookingToBookingDto(Booking booking) {
        return new BookingDto(booking.getId(), personMapper.convertFromPersonToPersonDto(booking.getPerson()));
    }

    @Override
    public Booking convertFromBookingDtoToBooking(BookingDto bookingDto) {
        return new Booking(bookingDto.id(), personMapper.convertFromPersonDtoToPerson(bookingDto.person()));
    }
}
