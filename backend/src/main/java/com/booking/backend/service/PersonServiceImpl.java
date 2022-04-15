package com.booking.backend.service;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.mapper.Mapper;
import com.booking.backend.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final Mapper mapper;

    public PersonServiceImpl(PersonRepository personRepository, Mapper mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    @Override
    public PersonDto getPersonById(Long id) {
        return personRepository.getPersonById(id);
    }


    @Override
    public void updatePerson(PersonDto personDto) {
        personRepository.save(mapper.convertFromPersonDtoToPerson(personDto));
    }
}
