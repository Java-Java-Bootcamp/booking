package com.booking.backend.mapper;

import com.booking.backend.dto.PersonDto;
import com.booking.backend.dto.SomeObjectDto;
import com.booking.backend.entity.Person;
import com.booking.backend.entity.SomeObject;
import org.mapstruct.Mapper;

@Mapper
public interface SomeObjectMapper {

    SomeObject convert(SomeObjectDto someObjectDto);

    SomeObjectDto convert(SomeObject someObject);
}
