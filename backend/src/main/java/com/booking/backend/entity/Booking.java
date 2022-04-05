package com.booking.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfBeginning;

    private LocalDate dateOfEnding;

    private Integer numbersOfGuests;

    @ManyToOne
    private User user;

    @ManyToOne
    private Organization organization;


}
