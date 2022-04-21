package com.booking.backend.service;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Person;
import com.booking.backend.mapper.PersonMapper;
import com.booking.backend.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper mapper;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void getPersonById() {
        personService.getPersonById(1L);
        verify(personRepository, times(1)).findById(1L);
    }

    @Test
    void updatePerson() {
        PersonDto personDto = new PersonDto(1L, "A");
        personService.updatePerson(personDto);
        verify(mapper, times(1)).convertFromPersonDtoToPerson(personDto);
        verify(personRepository, times(1)).save(any());
    }
}