package com.booking.backend.mapper;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Person;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person convert(PersonDto personDto) {
        return new Person(personDto.id(), personDto.name());
    }

    @Override
    public Person convertFromPersonDtoToPersonInController(Optional<PersonDto> personDto) {
        return personDto.map(value -> new Person(value.id(), value.name())).orElse(null);
    }

    @Override
    public PersonDto convertFromPersonToPersonDtoInController(Optional<Person> person) {
        return person.map(value -> new PersonDto(value.getId(), value.getName())).orElse(null);
    }
    @Override
    public PersonDto convert(Person person) {
        return new PersonDto(person.getId(), person.getName());
    }
}
