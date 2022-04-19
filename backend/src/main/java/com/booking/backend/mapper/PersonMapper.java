package com.booking.backend.mapper;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Person;

import java.util.Optional;

public interface PersonMapper {

    Person convertFromPersonDtoToPerson(PersonDto personDto);

    PersonDto convertFromPersonToPersonDto(Person person);

    PersonDto convertFromPersonToPersonDtoInController(Optional<Person> person);

    }
