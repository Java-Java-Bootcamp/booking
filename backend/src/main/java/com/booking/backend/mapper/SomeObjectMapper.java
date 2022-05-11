package com.booking.backend.mapper;

import com.booking.backend.dto.SomeObjectDto;
import com.booking.backend.entity.SomeObject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SomeObjectMapper {

    SomeObject convert(SomeObjectDto someObjectDto);

    SomeObjectDto convert(SomeObject someObject);
}
