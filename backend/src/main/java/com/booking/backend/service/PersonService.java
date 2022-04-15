package com.booking.backend.service;

import org.springframework.stereotype.Service;
import com.booking.backend.dto.PersonDto;

public interface PersonService {
    PersonDto getPersonById(Long id);

    void updatePerson(PersonDto personDto);
}
