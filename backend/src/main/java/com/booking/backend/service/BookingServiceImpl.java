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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private BookingMapper bookingMapper;

    @Override
    public List<BookingDto> getAllBookings(Integer pageNo, Integer pageSize, String sortBy) {
//        if (sortBy.equals("rate")) {
//            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("organization.rating"));
//            Page<Booking> pagedResult = bookingRepository.findAll(paging);
//            if (pagedResult.hasContent()) {
//                return pagedResult.getContent().stream()
//                        .map(bookingMapper::convertFromBookingToBookingDto)
//                        .collect(Collectors.toList());
//            } else {
//                return new ArrayList<>();
//            }
//        }
//        if (sortBy.equals("bill")) {
//            //сортировку надо переделывать потому что букинг теперь не содержит организацию
//            // можно подтягивать сюда просто организацию, тогда сортировка будет осуществляться вообще по организациям
//            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("organization.averageCheck"));
//            Page<Booking> pagedResult = bookingRepository.findAll(paging);
//            if (pagedResult.hasContent()) {
//                return pagedResult.getContent().stream()
//                        .map(bookingMapper::convertFromBookingToBookingDto)
//                        .collect(Collectors.toList());
//            } else {
//                return new ArrayList<>();
//            }
//        }
//        if (sortBy.equals("time")) {
//            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("dateOfBeginning"));
//            Page<Booking> pagedResult = bookingRepository.findAll(paging);
//            if (pagedResult.hasContent()) {
//                return pagedResult.getContent().stream()
//                        .map(bookingMapper::convertFromBookingToBookingDto)
//                        .collect(Collectors.toList());
//            } else {
//                return new ArrayList<>();
//            }
//        }
        return null;
    }



    @Override
    public void addNewBooking(BookingDto bookingDto) {
        bookingRepository.save(bookingMapper.convertFromBookingDtoToBooking(bookingDto));
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }
}
