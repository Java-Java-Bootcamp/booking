package com.booking.backend.mapper;

import com.booking.backend.dto.SomeObjectDto;
import com.booking.backend.entity.SomeObject;

public interface SomeObjectMapper {

    SomeObject convertFromSomeObjectDtoToSomeObject(SomeObjectDto someObjectDto);

    SomeObjectDto convertFromSomeObjectToSomeObjectDto(SomeObject someObject);
}
