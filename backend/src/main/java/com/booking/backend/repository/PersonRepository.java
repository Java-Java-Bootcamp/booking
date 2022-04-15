package com.booking.backend.repository;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    PersonDto getPersonById(Long id);
}
