package com.booking.backend.service;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {

//    List<Booking> findAllByBookedFalseAndOrganizationName(String nameOfOrganization);

    List<BookingDto> getAllBookings(Integer pageNo, Integer pageSize, String sortBy);

    void addNewBooking(BookingDto bookingDto);

    Optional<Booking> findById(Long id);

}
