package com.booking.backend.controller;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PersonController {
    private final PersonService personService;

    @GetMapping("/person/{id}")
    public PersonDto getById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/person")
    public void addNewBooking(@RequestBody PersonDto personDto) {
        personService.updatePerson(personDto);
    }
}
