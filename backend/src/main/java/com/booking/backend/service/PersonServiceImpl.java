package com.booking.backend.service;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.mapper.PersonMapper;
import com.booking.backend.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper mapper;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    @Override
    public PersonDto getPersonById(Long id) {
        return mapper.convertFromPersonToPersonDtoInController(personRepository.findById(id));
    }


    @Override
    public void updatePerson(PersonDto personDto) {
        personRepository.save(mapper.convert(personDto));
    }
}
