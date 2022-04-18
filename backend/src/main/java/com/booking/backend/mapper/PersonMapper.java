package com.booking.backend.mapper;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Person;

import java.util.Optional;

public interface PersonMapper {

    Person convertFromPersonDtoToPerson(PersonDto personDto);

    PersonDto convertFromPersonToPersonDtoInController(Optional<Person> person);

    PersonDto convertFromPersonToPersonDto(Person person);

    Person convertFromPersonDtoToPersonInController(Optional<PersonDto> personDto);

    }
