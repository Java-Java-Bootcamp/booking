package com.booking.backend.service;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.mapper.PersonMapper;
import com.booking.backend.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper mapper;

    @Override
    public PersonDto getPersonById(Long id) {
        return mapper.convert(personRepository.findById(id));
    }


    @Override
    public void updatePerson(PersonDto personDto) {
        personRepository.save(mapper.convert(personDto));
    }
}
