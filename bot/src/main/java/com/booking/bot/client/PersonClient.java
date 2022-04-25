package com.booking.bot.client;

import com.booking.bot.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url="localhost:8080")
public interface PersonClient {

    @GetMapping("/person")
    @RequestMapping(value = "/person", params = "id")
    PersonDto getPersonById(@RequestParam Long id);

    @PostMapping("/person")
     void addNewBooking(@RequestBody PersonDto personDto);
}

