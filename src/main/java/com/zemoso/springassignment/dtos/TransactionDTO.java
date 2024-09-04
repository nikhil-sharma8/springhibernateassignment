package com.zemoso.springassignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private String type;
    private Double amount;
    private LocalDate date;
}
