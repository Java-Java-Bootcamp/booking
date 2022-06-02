package com.booking.backend.service;

import com.booking.backend.usecases.BookingDto;
import com.booking.backend.usecases.PersonDto;
import com.booking.backend.usecases.BookingMapper;
import com.booking.backend.entities.BookingRepository;
import com.booking.backend.usecases.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private BookingMapper bookingMapper;
    @InjectMocks
    private BookingService bookingService;

    @Test
    void addNewBooking() {
        BookingDto bookingDto = new BookingDto(1L, new PersonDto(1L, "asd"));
        bookingService.addNewBooking(bookingDto);
        verify(bookingMapper, times(1)).convert(bookingDto);
        verify(bookingRepository, times(1)).save(any());
    }
}