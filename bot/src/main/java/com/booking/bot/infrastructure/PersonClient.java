package com.booking.bot.infrastructure;

import com.booking.bot.view.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "localhost:8080", name = "person-controller")
public interface PersonClient {

    @RequestMapping(value = "/person", params = {"id"}, method = RequestMethod.GET)
    PersonDto getById(@RequestParam Long id);

    @PostMapping("/person")
    void addNewBooking(@RequestBody PersonDto personDto);
}

