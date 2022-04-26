package com.booking.backend.service;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.mapper.BookingMapper;
import com.booking.backend.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public void addNewBooking(BookingDto bookingDto) {
        bookingRepository.save(bookingMapper.convertFromBookingDtoToBooking(bookingDto));
    }
}
