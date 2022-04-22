package com.booking.backend.mapper;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Person;

import java.util.Optional;

public interface PersonMapper {

    Person convert(PersonDto personDto);

    PersonDto convertFromPersonToPersonDtoInController(Optional<Person> person);

    PersonDto convert(Person person);

    Person convertFromPersonDtoToPersonInController(Optional<PersonDto> personDto);

    }
