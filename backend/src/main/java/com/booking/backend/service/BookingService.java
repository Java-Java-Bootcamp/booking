package com.booking.backend.service;

import com.booking.backend.dto.BookingDto;

import java.util.List;

public interface BookingService {
    void addNewBooking(BookingDto bookingDto);
}
