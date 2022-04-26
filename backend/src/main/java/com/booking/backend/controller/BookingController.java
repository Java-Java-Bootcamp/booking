package com.booking.backend.controller;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/bookings")
    public void addNewBooking(@RequestBody BookingDto bookingDto) {
        bookingService.addNewBooking(bookingDto);
    }

}
