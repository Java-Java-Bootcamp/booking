package com.booking.backend.service;

import com.booking.backend.dto.PersonDto;

public interface PersonService {
    PersonDto getPersonById(Long id);

    void updatePerson(PersonDto personDto);
}
