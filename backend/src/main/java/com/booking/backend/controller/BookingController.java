package com.booking.backend.controller;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;
import com.booking.backend.service.BookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping(value = "/booking", params = "nameOfOrganization")
    public List<Booking> getAllBookings(@RequestParam String nameOfOrganization) {
        return bookingService.findAllByBookedFalseAndOrganizationName(nameOfOrganization);
    }

    @GetMapping("/booking")
    @RequestMapping(value = "/booking", params = {"pageNo","pageSize","sortBy"})
    public List<BookingDto> getAll(@RequestParam Integer pageNo,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String sortBy) {
        return bookingService.getAllBookings(pageNo, pageSize, sortBy);
    }

//    @GetMapping("/bookinga")
//    public List<Booking> getAllBookings1(String name) {
//        return bookingService.getTopByOrganizationRating(name);
//    }

//    @GetMapping("/bookingss")
//    public List<Booking> getAllBookings1(@RequestParam String nameOfOrganization) {
//        return bookingService.getAllByOrganizationNameAndOrganizationRating(nameOfOrganization);
//    }
}
