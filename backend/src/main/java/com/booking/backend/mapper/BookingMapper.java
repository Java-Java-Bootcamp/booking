package com.booking.backend.mapper;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingDto convert (Booking booking);

    Booking convert(BookingDto bookingDto);
}
