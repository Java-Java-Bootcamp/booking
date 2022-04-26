package com.booking.backend.mapper;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Person;

import java.util.Optional;

public interface PersonMapper {

    Person convert(PersonDto personDto);

    PersonDto convert(Person person);

    PersonDto convertFromPersonToPersonDtoInController(Optional<Person> person);

    }
