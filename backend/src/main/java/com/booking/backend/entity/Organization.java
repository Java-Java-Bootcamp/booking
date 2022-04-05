package com.booking.backend.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String schedule;

    private Integer numbersOfTables;

    private Double averageCheck;

    private Double rating;

    @OneToMany
    private List<Cuisine> cuisine;

}
