package com.booking.backend.mapper;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.dto.SomeObjectDto;
import com.booking.backend.entity.Person;
import com.booking.backend.entity.SomeObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface SomeObjectMapper {

    SomeObject convert(SomeObjectDto someObjectDto);

    SomeObjectDto convert(SomeObject someObject);
}
