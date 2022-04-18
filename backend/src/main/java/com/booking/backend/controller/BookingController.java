package com.booking.backend.controller;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/booking")
    @RequestMapping(value = "/booking", params = {"pageNo","pageSize","sortBy"})
    public List<BookingDto> getAll(@RequestParam Integer pageNo,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String sortBy) {
        return bookingService.getAllBookings(pageNo, pageSize, sortBy);
    }

    @PostMapping("/bookings")
    public void addNewBooking(@RequestBody BookingDto bookingDto) {
        bookingService.addNewBooking(bookingDto);
    }

}
