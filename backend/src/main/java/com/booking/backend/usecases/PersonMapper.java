package com.booking.backend.usecases;

import com.booking.backend.entities.Person;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person convert(PersonDto personDto);

    PersonDto convert(Person person);

    PersonDto convert(Optional<Person> person);

}
