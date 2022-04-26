package com.booking.backend.mapper;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BookingMapper {

    BookingDto convert (Booking booking);

    Booking convert(BookingDto bookingDto);
}
