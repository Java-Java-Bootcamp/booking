package com.booking.backend.service;

import com.booking.backend.dto.BookingDto;

import java.util.List;

public interface BookingService {
    List<BookingDto> getAllBookings(Integer pageNo, Integer pageSize, String sortBy);

    void addNewBooking(BookingDto bookingDto);
}
