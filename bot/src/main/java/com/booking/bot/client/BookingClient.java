package com.booking.bot.client;

import com.booking.bot.dto.BookingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url="localhost:8080")
public interface BookingClient {
    @GetMapping("/booking")
    @RequestMapping(value = "/booking", params = {"pageNo","pageSize","sortBy"})
    List<BookingDto> getAll(@RequestParam Integer pageNo,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String sortBy);

    @PostMapping("/bookings")
    void addNewBooking(@RequestBody BookingDto bookingDto);

}

