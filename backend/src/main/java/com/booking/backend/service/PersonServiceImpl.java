package com.booking.backend.service;

import com.booking.backend.dto.OrganizationDto;
import com.booking.backend.dto.PersonDto;
import com.booking.backend.mapper.BookingMapper;
import com.booking.backend.mapper.BookingMapperImpl;
import com.booking.backend.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final BookingMapper bookingMapper;

    public PersonServiceImpl(PersonRepository personRepository, BookingMapper bookingMapper) {
        this.personRepository = personRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public PersonDto getPersonById(Long id) {
        return personRepository.getPersonById(id);
    }


    @Override
    public void updatePerson(PersonDto personDto) {
        personRepository.save(bookingMapper.convertFromPersonDtoToPerson(personDto));
    }
}
