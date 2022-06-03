package com.booking.bot.infrastructure;

import com.booking.bot.view.BookingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "localhost:8080", name = "booking-controller")
public interface BookingClient {

    @PostMapping("/bookings")
    void addBooking(@RequestBody BookingDto bookingDto);

}

