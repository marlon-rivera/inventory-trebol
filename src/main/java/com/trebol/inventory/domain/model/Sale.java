package com.trebol.inventory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale {

    private Long id;
    private Client client;
    private LocalDate date;
    private BigDecimal totalPrice;
    private String idInCharge;

}
