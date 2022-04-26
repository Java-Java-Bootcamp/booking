package com.booking.bot.client;

import com.booking.bot.dto.BookingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "localhost:8080", name = "booking-client")
public interface BookingClient {

    @PostMapping("/bookings")
    void addNewBooking(@RequestBody BookingDto bookingDto);

}

