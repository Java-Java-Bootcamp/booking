package com.booking.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOfBeginning;

    private LocalDateTime dateOfEnding;

    private Integer numbersOfGuests;

    private boolean booked;

    @ManyToOne
    private User user;

    @ManyToOne
    private Organization organization;


}
