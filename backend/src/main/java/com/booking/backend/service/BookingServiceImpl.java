package com.booking.backend.service;

import com.booking.backend.dto.BookingDto;
import com.booking.backend.entity.Booking;
import com.booking.backend.mapper.BookingMapper;
import com.booking.backend.mapper.OrganizationMapper;
import com.booking.backend.repository.BookingRepository;
import com.booking.backend.repository.OrganizationRepository;
import com.booking.backend.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private BookingMapper bookingMapper;

    @Override
    public void addNewBooking(BookingDto bookingDto) {
        bookingRepository.save(bookingMapper.convertFromBookingDtoToBooking(bookingDto));
    }
}
