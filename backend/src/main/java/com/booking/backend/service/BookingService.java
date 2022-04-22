package com.booking.backend.service;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;

import java.util.List;

public interface BookingService {

    void addNewBooking(BookingDto bookingDto);

}
