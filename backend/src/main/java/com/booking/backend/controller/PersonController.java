package com.booking.backend.controller;
import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.service.PersonService;
import org.springframework.web.bind.annotation.*;
import com.booking.backend.dto.PersonDto;
@RestController
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/person")
    @RequestMapping(value = "/person", params = "id")
    public PersonDto getPersonById(@RequestParam Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/person")
    public void addNewBooking(@RequestBody PersonDto personDto) {
        personService.updatePerson(personDto);

    }
}
