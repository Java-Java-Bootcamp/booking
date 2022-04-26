package com.booking.backend.controller;

import com.booking.backend.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.booking.backend.dto.PersonDto;

@RequiredArgsConstructor
@RestController
public class PersonController {
    private final PersonService personService;

    @RequestMapping(value = "/person", params = {"id"}, method = RequestMethod.GET)
    public PersonDto getPersonById(@RequestParam Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/person")
    public void addNewBooking(@RequestBody PersonDto personDto) {
        personService.updatePerson(personDto);
    }
}
