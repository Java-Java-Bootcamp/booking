package com.booking.backend.service;

import com.booking.backend.usecases.PersonDto;
import com.booking.backend.usecases.PersonMapper;
import com.booking.backend.entities.PersonRepository;
import com.booking.backend.usecases.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper mapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void getPersonById() {
        personService.getPersonById(1L);
        verify(personRepository, times(1)).findById(1L);
    }

    @Test
    void updatePerson() {
        PersonDto personDto = new PersonDto(1L, "A");
        personService.updatePerson(personDto);
        verify(mapper, times(1)).convert(personDto);
        verify(personRepository, times(1)).save(any());
    }
}