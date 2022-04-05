package com.booking.backend.controller;

import com.booking.backend.entity.Booking;
import com.booking.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/booking")
    public List<Booking> getAllBookings(@RequestParam String nameOfOrganization) {
        return bookingService.getAllByOrganizationName(nameOfOrganization);
    }
}
