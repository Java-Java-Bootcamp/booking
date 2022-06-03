package com.booking.backend.usecases;

import com.booking.backend.entities.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper mapper;

    public PersonDto getPersonById(Long id) {
        return mapper.convert(personRepository.findById(id));
    }


    public void updatePerson(PersonDto personDto) {
        personRepository.save(mapper.convert(personDto));
    }
}
