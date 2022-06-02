package com.booking.bot.service;

import com.booking.bot.infrastructure.PersonClient;
import com.booking.bot.view.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonService {
    private final PersonClient personClient;

    public void addPerson(PersonDto personDto) {
        personClient.addNewBooking(personDto);
    }
}