package com.booking.backend.controller;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/bookings")
    public void addNewBooking(@RequestBody BookingDto bookingDto) {
        bookingService.addNewBooking(bookingDto);
    }

}
