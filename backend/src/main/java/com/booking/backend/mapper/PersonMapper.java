package com.booking.backend.mapper;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Person;
import org.mapstruct.Mapper;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person convert(PersonDto personDto);

    PersonDto convert(Person person);

    PersonDto convert(Optional<Person> person);

}
