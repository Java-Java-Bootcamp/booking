package com.booking.backend.usecases;

import com.booking.backend.entities.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingDto convert(Booking booking);

    Booking convert(BookingDto bookingDto);
}
