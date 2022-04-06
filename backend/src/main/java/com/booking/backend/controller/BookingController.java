package com.booking.backend.controller;

import com.booking.backend.entity.Booking;
import com.booking.backend.service.BookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/booking")
    public List<Booking> getAllBookings(@RequestParam String nameOfOrganization) {
        return bookingService.findAllByBookedFalseAndOrganizationName(nameOfOrganization);
    }

    @GetMapping("/booking/")
    public List<Booking> getAll(@RequestParam Integer limit,
                                @RequestParam Integer offset) {
        return bookingService.getAllBookings(limit, offset);
    }
}
