package com.booking.backend.usecases;

import com.booking.backend.entities.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public void addNewBooking(BookingDto bookingDto) {
        bookingRepository.save(bookingMapper.convert(bookingDto));
    }
}
