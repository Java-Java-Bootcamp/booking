package com.booking.backend.mapper;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingDto convert (Booking booking);

    Booking convert(BookingDto bookingDto);
}
