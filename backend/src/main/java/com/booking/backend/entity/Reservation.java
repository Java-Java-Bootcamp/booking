package com.booking.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer beginning;
    private Integer ending;
    //если это значение равняется нулю, то значит все столики на это время заняты и в кнопках отображаться этот слот не будет
    private Integer numbersOfTables;

}
