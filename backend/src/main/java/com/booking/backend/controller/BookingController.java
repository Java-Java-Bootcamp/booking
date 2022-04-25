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

    @GetMapping("/booking/pageNo={pageNo},pageSize={pageSize},sortBy={sortBy}")
    public List<BookingDto> getAll(@PathVariable Integer pageNo,
                                   @PathVariable Integer pageSize,
                                   @PathVariable String sortBy) {
        return bookingService.getAllBookings(pageNo, pageSize, sortBy);
    }

    @PostMapping("/bookings")
    public void addNewBooking(@RequestBody BookingDto bookingDto) {
        bookingService.addNewBooking(bookingDto);
    }

}
