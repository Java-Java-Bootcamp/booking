package com.booking.bot.client;

import com.booking.bot.dto.BookingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "localhost:8080", name = "booking-client")
public interface BookingClient {
    @GetMapping("/booking/pageNo={pageNo},pageSize={pageSize},sortBy={sortBy}")
    List<BookingDto> getAll(@PathVariable Integer pageNo,
                            @PathVariable Integer pageSize,
                            @PathVariable String sortBy);

    @PostMapping("/bookings")
    void addNewBooking(@RequestBody BookingDto bookingDto);

}

