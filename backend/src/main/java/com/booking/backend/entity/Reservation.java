package com.booking.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer beginning;
    private Integer ending;
    //если это значение равняется нулю, то значит все столики на это время заняты и в кнопках отображаться этот слот не будет
    private Integer numbersOfTables;

}
