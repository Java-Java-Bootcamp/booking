package com.booking.backend.service;

import com.booking.backend.dto.ReservationDto;
import com.booking.backend.repository.ReservRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservRepo reservRepo;

    public ReservationServiceImpl(ReservRepo reservRepo) {
        this.reservRepo = reservRepo;
    }

    @Override
    public ReservationDto findReservationById(Long id) {
        return reservRepo.findById(id).stream().map(e -> new ReservationDto(e.getId(),
                e.getBeginning(),
                e.getEnding(),
                e.getNumbersOfTables())).findFirst().get();
    }
}
