package com.booking.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String schedule;

    private Integer numbersOfTables;

    private Double averageCheck;

    private Double rating;

    @ManyToMany
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany
    private List<Cuisine> cuisine = new ArrayList<>();


}
