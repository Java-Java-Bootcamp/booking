package com.booking.backend.mapper;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PersonMapperImplTest {

    private PersonMapperImpl personMapper = new PersonMapperImpl();
    private Person personEx = new Person(1L, "Alex");
    private PersonDto personDtoEx = new PersonDto(1L, "Alex");

    @Test
    void convertFromPersonDtoToPerson() {
        assertEquals(personEx, personMapper.convert(personDtoEx));
    }

    @Test
    void convertFromPersonDtoToPersonInController() {
        assertEquals(personDtoEx, personMapper.convertFromPersonToPersonDtoInController(java.util.Optional.ofNullable(personEx)));
    }

    @Test
    void convertFromPersonToPersonDto() {
        assertEquals(personDtoEx, personMapper.convert(personEx));
    }
}